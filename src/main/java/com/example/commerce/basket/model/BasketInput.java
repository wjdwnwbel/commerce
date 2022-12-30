package com.example.commerce.basket.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasketInput {
	private long item_id;
	private long member_id;
}
