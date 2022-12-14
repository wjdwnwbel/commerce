package com.example.commerce.admin.service;

import com.example.commerce.admin.entity.Category;
import com.example.commerce.admin.model.CategoryInput;
import com.example.commerce.result.ServiceResult;
import java.util.List;

public interface CategoryService {
	ServiceResult add(CategoryInput parameter);

	List<Category> list();

	ServiceResult deleteCategory(String name);
}
