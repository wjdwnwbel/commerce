package com.example.commerce.member.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberInput {
	private Long id;
	private String email;
	private String name;
	private String phoneNumber;
	private String password;

	private String zipcode;
	private String address;
	private String addressDetail;
}
