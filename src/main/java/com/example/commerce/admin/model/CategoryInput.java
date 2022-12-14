package com.example.commerce.admin.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryInput {
	private String name;
	private Boolean usingYn;
}
