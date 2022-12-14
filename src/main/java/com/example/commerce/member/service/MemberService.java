package com.example.commerce.member.service;

import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.model.MemberInput;
import com.example.commerce.member.param.MemberParam;
import com.example.commerce.result.ServiceResult;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService extends UserDetailsService {

	ServiceResult register(MemberInput parameter);
	MemberDto detail(String email);
	ServiceResult updateMember(MemberInput parameter);


	// 관리자
	List<Member> list();
	ServiceResult updateStatus(MemberParam parameter);
	ServiceResult updateAdmin(MemberParam parameter);
}
