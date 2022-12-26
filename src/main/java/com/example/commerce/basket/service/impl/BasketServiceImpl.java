package com.example.commerce.basket.service.impl;

import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.repository.ItemRepository;
import com.example.commerce.basket.dto.BasketDto;
import com.example.commerce.basket.entity.Basket;
import com.example.commerce.basket.model.BasketInput;
import com.example.commerce.basket.repository.BasketRepository;
import com.example.commerce.basket.service.BasketService;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.repository.MemberRepository;
import com.example.commerce.result.ServiceResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

	private final BasketRepository basketRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;

	@Override
	public ServiceResult add(BasketInput parameter) {

		// 검색해서 회원 아이디와 상품아이디와 같은 것이 있으면 count+1
		Optional<Basket> optionalBasket = basketRepository.findByMember_IdAndItem_Id(parameter.getMember_id(), parameter.getItem_id());
		if(optionalBasket.isPresent()) {
			Basket basket = optionalBasket.get();
			basket.setAmount(basket.getAmount()+1);

			basketRepository.save(basket);

			return new ServiceResult(true);
		}


		// 없으면 생성

		Optional<Member> optionalMember = memberRepository.findById(parameter.getMember_id());
		if(optionalMember.isEmpty()) {
			return new ServiceResult(false, "해당하는 회원이 없습니다.");
		}
		Member member = optionalMember.get();

		Optional<Item> optionalItem = itemRepository.findById(parameter.getItem_id());
		if(optionalItem.isEmpty()) {
			return new ServiceResult(false, "해당하는 상품이 없습니다.");
		}
		Item item = optionalItem.get();

		Basket basket = Basket.builder()
			.item(item)
			.member(member)
			.amount(1)
			.registerDate(LocalDate.now())
			.build();


		basketRepository.save(basket);


		return new ServiceResult(true);
	}


	@Override
	public List<BasketDto> list(String id) {

		List<Basket> baskets = basketRepository.findAllByMember_Id(Long.parseLong(id));
		if(baskets.isEmpty()) {
			throw new RuntimeException("장바구니에 저장된 상품이 없습니다. ");
		}

		List<BasketDto> basketDtos = new ArrayList<>();

		for(Basket b : baskets) {
			basketDtos.add(BasketDto.of(b));
		}

		System.out.println(basketDtos);

		return basketDtos;
	}

	@Override
	public List<Basket> dayBasket(LocalDate startDate, LocalDate endDate) {
		return basketRepository.findAllByRegisterDateBetween(startDate, endDate);
	}
}
