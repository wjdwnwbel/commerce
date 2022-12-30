package com.example.commerce.admin.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ItemInput {

	private Integer category_id;
	private String name;
	private String product;
	private String image;
	private Integer price;
	private Integer amount;
}
