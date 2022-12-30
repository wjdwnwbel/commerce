package com.example.commerce.basket.dto;

import com.example.commerce.admin.entity.Item;
import com.example.commerce.basket.entity.Basket;
import com.example.commerce.member.entity.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {

	private Member member;

	private Item item;

	private Integer amount;
	private LocalDate registerDate;


	public static BasketDto of(Basket basket) {
		return BasketDto.builder()
			.member(basket.getMember())
			.item(basket.getItem())
			.amount(basket.getAmount())
			.registerDate(basket.getRegisterDate())
			.build();
	}
}
