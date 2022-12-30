package com.example.commerce.basket.controller;

import com.example.commerce.admin.model.CategoryInput;
import com.example.commerce.basket.dto.BasketDto;
import com.example.commerce.basket.entity.Basket;
import com.example.commerce.basket.model.BasketInput;
import com.example.commerce.basket.service.BasketService;
import com.example.commerce.result.ServiceResult;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketController {

	private final BasketService basketService;

	@PostMapping("/member/basket/add")
	public String basketAdd(Model model, @RequestBody BasketInput parameter) {

		ServiceResult result = basketService.add(parameter);

		return result.getMessage();
	}

	@GetMapping("/member/basket/list")
	public List<BasketDto> getBasket(Principal principal) {
		String id = principal.getName();	// id
		List<BasketDto> result = basketService.list(id);

		return result;
	}

	@GetMapping("/member/basket/dayList")
	List<Basket> dayBaskets(@RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate startDate, @RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate endDate) {
		return basketService.dayBasket(startDate,endDate);
	}
}
