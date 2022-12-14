package com.example.commerce.member.dto;

import com.example.commerce.member.entity.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	private String email;
	private String name;
	private String phoneNumber;
	private String password;
	private LocalDateTime registerDate;

	private String zipcode;
	private String address;
	private String addressDetail;

	public static MemberDto of(Member member) {
		return MemberDto.builder()
			.email(member.getEmail())
			.name(member.getName())
			.phoneNumber(member.getPhoneNumber())
			.password(member.getPassword())
			.registerDate(member.getRegisterDate())

			.zipcode(member.getZipcode())
			.address(member.getAddress())
			.addressDetail(member.getAddressDetail())

			.build();
	}
}
