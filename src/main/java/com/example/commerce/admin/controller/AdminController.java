package com.example.commerce.admin.controller;

import com.example.commerce.admin.entity.Category;
import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.model.CategoryInput;
import com.example.commerce.admin.model.ItemInput;
import com.example.commerce.admin.param.ItemParam;
import com.example.commerce.admin.repository.ItemRepository;
import com.example.commerce.admin.service.CategoryService;
import com.example.commerce.admin.service.ItemService;
import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.param.MemberParam;
import com.example.commerce.member.service.MemberService;
import com.example.commerce.result.ServiceResult;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
	private final ItemService itemService;
	private final MemberService memberService;
	private final CategoryService categoryService;


	@GetMapping("/admin/member/list")
	public List<Member> list(Model model) {

		List<Member> members = memberService.list();

		return members;
	}

	@PostMapping("/admin/member/updateStatus")
	public String updateStatus(Model model, @RequestBody MemberParam parameter) {

		ServiceResult result = memberService.updateStatus(parameter);

		return result.getMessage();
	}

	@PostMapping("/admin/member/updateAdmin")
	public String updateAdmin(Model model, @RequestBody MemberParam parameter) {

		ServiceResult result = memberService.updateAdmin(parameter);

		return result.getMessage();
	}


	@PostMapping("/admin/item/add")
	public String add(Model model, ItemInput parameter) {

		ServiceResult result = itemService.add(parameter);
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "/error";
		}

		model.addAttribute("result", result);
		return result.getMessage();
	}
	@GetMapping("/admin/item/list")
	public List<Item> list() {
		List<Item> list = itemService.list();

		return list;
	}
	@PostMapping("/admin/item/update")
	public String updateItem(Model model, @RequestBody ItemParam parameter) {
		ServiceResult result = itemService.updateItem(parameter);

		return result.getMessage();
	}
	@DeleteMapping("/admin/item/delete")
	public String deleteItem(@RequestParam String name) {
		ServiceResult result = itemService.deleteItem(name);

		return result.getMessage();
	}


	@GetMapping("/admin/category/list")
	public List<Category> categoryList(Model model) {
		List<Category> categories = categoryService.list();

		return categories;
	}
	@PostMapping("/admin/category/add")
	public String categoryAdd(Model model, @RequestBody CategoryInput parameter) {
		ServiceResult result = categoryService.add(parameter);

		return result.getMessage();
	}
	@DeleteMapping("/admin/category/delete")
	public String deleteCategory(@RequestParam String name) {
		ServiceResult result = categoryService.deleteCategory(name);

		return result.getMessage();
	}
}
