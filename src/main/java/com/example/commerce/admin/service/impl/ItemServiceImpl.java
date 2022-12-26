package com.example.commerce.admin.service.impl;

import com.example.commerce.admin.entity.Category;
import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.model.ItemInput;
import com.example.commerce.admin.param.ItemParam;
import com.example.commerce.admin.repository.CategoryRepository;
import com.example.commerce.admin.repository.ItemRepository;
import com.example.commerce.admin.service.ItemService;
import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.model.MemberInput;
import com.example.commerce.member.param.MemberParam;
import com.example.commerce.member.repository.MemberRepository;
import com.example.commerce.member.service.MemberService;
import com.example.commerce.result.ServiceResult;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public ServiceResult add(ItemInput parameter) {
		Optional<Item> optionalItem = itemRepository.findByName(parameter.getName());

		if(optionalItem.isPresent()) {
			return new ServiceResult(false, "같은 이름의 상품이 이미 존재합니다. ");
		}

		Optional<Category> optionalCategory = categoryRepository.findById(parameter.getCategory_id());
		if(optionalCategory.isEmpty()) {
			return new ServiceResult(false, "해당하는 카테고리가 없습니다. 다른 카테고리를 선택해주세요");
		}

		Category category = optionalCategory.get();
		if(!category.getUsingYn()) {
			return new ServiceResult(false, "사용할 수 없는 카테고리입니다. 다른 카테고리를 선택해주세요");
		}

		Item item = Item.builder()
			.name(parameter.getName())
			.category(category)
			.product(parameter.getProduct())
			.image(parameter.getImage())
			.price(parameter.getPrice())
			.amount(parameter.getAmount())
			.registerDate(LocalDateTime.now())
			.build();

		itemRepository.save(item);
		return new ServiceResult(true);
	}

	@Override
	public ServiceResult updateItem(ItemParam parameter) {
		Optional<Item> optionalItem = itemRepository.findByName(parameter.getName());

		if(!optionalItem.isPresent()) {
			return new ServiceResult(false, "해당하는 상품이 없습니다. ");
		}

		Item item = optionalItem.get();
		item.setName(parameter.getName());
		item.setProduct(parameter.getProduct());
		item.setImage(parameter.getImage());
		item.setPrice(parameter.getPrice());
		item.setAmount(parameter.getAmount());
		itemRepository.save(item);

		return new ServiceResult(true);
	}

	@Override
	public ServiceResult deleteItem(String name) {
		Optional<Item> deleteItem = itemRepository.findByName(name);

		if(!deleteItem.isPresent()) {
			return new ServiceResult(false, "해당 이름에 존재하는 상품이 없습니다. ");
		}

		long id = Math.toIntExact(deleteItem.get().getId());

		itemRepository.deleteById(id);

		return new ServiceResult(true);
	}

	@Transactional
	public List<Item> search(String name) {
		List<Item> itemList = itemRepository.findByNameContaining(name);

		return itemList;
	}


	@Override
	public List<Item> list() {
		List<Item> list = itemRepository.findAll();

		return list;
	}




}
