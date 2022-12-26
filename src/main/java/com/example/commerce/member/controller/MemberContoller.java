package com.example.commerce.member.controller;

import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.service.ItemService;
import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.model.MemberInput;
import com.example.commerce.member.service.MemberService;
import com.example.commerce.result.ServiceResult;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberContoller {

	private final MemberService memberService;
	private final ItemService itemService;

	@GetMapping("/member/loginSu")
	public String loginSu() {
		return "loginSuccess";
	}

	@GetMapping("/member/register")
	public String register() {

		return "member/register";
	}
	@PostMapping("/member/register")
	public String registerSubmit(Model model, @RequestBody MemberInput parameter) {

		ServiceResult result = memberService.register(parameter);
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "/error";
		}

		model.addAttribute("result", result);
		return "member/register_complete";
	}


	@GetMapping("/member/info")
	public MemberDto memberInfo(Principal principal, Model model) {

		String id = principal.getName();	// id 값이 나옴 : 1

		MemberDto detail = memberService.detail(id);

		return detail;
	}
	@PostMapping("/member/info")
	public String memberInfoSubmit(Principal principal, @RequestBody MemberInput parameter,Model model) {

		String id = principal.getName();

		// 기존 비밀번호 확인
		String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
		MemberDto passwordResult = memberService.detail(id);
		if(passwordResult.getPassword().equals(encPassword)) {
			return "/error";
		}

		ServiceResult result = memberService.updateMember(parameter);
		if(!result.isResult()) {
			model.addAttribute("message", result.getMessage());
			return "/error";
		}

		return "redirect:/member/info";
	}

	@GetMapping("/member/item/list")
	public List<Item> list() {
		List<Item> list = itemService.list();

		return list;
	}

	@GetMapping("/member/item/search")
	public List<Item> search(@RequestParam String name) {
		List<Item> itemList = itemService.search(name);

		return itemList;
	}


}
