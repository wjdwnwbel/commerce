package com.example.commerce.admin.service.impl;

import com.example.commerce.admin.entity.Category;
import com.example.commerce.admin.model.CategoryInput;
import com.example.commerce.admin.repository.CategoryRepository;
import com.example.commerce.admin.service.CategoryService;
import com.example.commerce.member.entity.Member;
import com.example.commerce.result.ServiceResult;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public ServiceResult add(CategoryInput parameter) {
		Optional<Category> optionalCategory = categoryRepository.findByName(parameter.getName());

		if(optionalCategory.isPresent()) {
			return new ServiceResult(false, "같은 카테고리가 이미 존재합니다. ");
		}

		Category category = Category.builder()
			.name(parameter.getName())
			.usingYn(parameter.getUsingYn())
			.build();

		categoryRepository.save(category);
		return new ServiceResult(true);
	}

	@Override
	public List<Category> list() {
		List<Category> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public ServiceResult deleteCategory(String name) {
		Optional<Category> deleteCategory = categoryRepository.findByName(name);
		
		if(!deleteCategory.isPresent()) {
			return new ServiceResult(false, "이름에 해당하는 카테고리가 존재하지 않습니다.");
		}

		int id = deleteCategory.get().getId();

		categoryRepository.deleteById(id);

		return new ServiceResult(true);
	}
}
