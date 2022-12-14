package com.example.commerce.member.service.impl;

import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.model.MemberInput;
import com.example.commerce.member.param.MemberParam;
import com.example.commerce.member.repository.MemberRepository;
import com.example.commerce.member.service.MemberService;
import com.example.commerce.result.ServiceResult;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public ServiceResult register(MemberInput parameter) {
		String email = parameter.getEmail();

		Optional<Member> optionalMember = memberRepository.findByEmail(email);

		if(optionalMember.isPresent()) {
			return new ServiceResult(false, "회원 정보가 이미 존재합니다. ");
		}
		String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());


		Member member =Member.builder()
			.email(parameter.getEmail())
			.name(parameter.getName())
			.phoneNumber(parameter.getPhoneNumber())
			.password(encPassword)
			.registerDate(LocalDateTime.now())
			.build();

		memberRepository.save(member);

		return new ServiceResult(true);
	}

	@Override
	public MemberDto detail(String id) {
		Optional<Member> optionalMember = memberRepository.findById(Long.valueOf(id));
		if(!optionalMember.isPresent()) {
			return null;
		}

		Member member = optionalMember.get();

		return MemberDto.of(member);
	}

	@Override
	public ServiceResult updateMember(MemberInput parameter) {
		Long id = parameter.getId();

		Optional<Member> optionalMember = memberRepository.findById(id);

		if(!optionalMember.isPresent()) {
			return new ServiceResult(false, "회원 정보가 존재하지 않습니다. ");
		}

		Member member = optionalMember.get();
		member.setPhoneNumber(parameter.getPhoneNumber());
		member.setZipcode(parameter.getZipcode());
		member.setAddress(parameter.getAddress());
		member.setAddressDetail(parameter.getAddressDetail());
		member.setUpdateDate(LocalDateTime.now());
		memberRepository.save(member);

		return new ServiceResult(true);
	}



	// 관리자
	@Override
	public List<Member> list() {
		List<Member> list = memberRepository.findAll();

		return list;
	}
	@Override
	public ServiceResult updateStatus(MemberParam parameter) {

		Optional<Member> optionalMember = memberRepository.findById(parameter.getId());
		if(!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
		}

		Member member = optionalMember.get();

		member.setStatus(parameter.getStatus());
		memberRepository.save(member);

		return new ServiceResult(true);
	}

	@Override
	public ServiceResult updateAdmin(MemberParam parameter) {
		Optional<Member> optionalMember = memberRepository.findById(parameter.getId());
		if(!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
		}

		Member member = optionalMember.get();

		member.setAdminYn(parameter.getAdminYn());
		memberRepository.save(member);

		return new ServiceResult(true);
	}

	////////////////////////////////
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Member> optionalMember = memberRepository.findByEmail(email);

		if(!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
		}

		Member member = optionalMember.get();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (member.getAdminYn()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new User(member.getId().toString(), member.getPassword(), grantedAuthorities);
	}


}
