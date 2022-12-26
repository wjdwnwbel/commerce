package com.example.commerce.basket.service;

import com.example.commerce.basket.dto.BasketDto;
import com.example.commerce.basket.entity.Basket;
import com.example.commerce.basket.model.BasketInput;
import com.example.commerce.result.ServiceResult;
import java.time.LocalDate;
import java.util.List;

public interface BasketService {

	ServiceResult add(BasketInput parameter);

	List<BasketDto> list(String id);
	List<Basket> dayBasket(LocalDate startDate, LocalDate endDate);
}
