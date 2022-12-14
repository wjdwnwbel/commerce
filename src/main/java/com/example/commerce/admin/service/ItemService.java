package com.example.commerce.admin.service;

import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.model.ItemInput;
import com.example.commerce.admin.param.ItemParam;
import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.model.MemberInput;
import com.example.commerce.member.param.MemberParam;
import com.example.commerce.result.ServiceResult;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ItemService {

	List<Item> list();

	ServiceResult add(ItemInput parameter);

	ServiceResult updateItem(ItemParam parameter);

	ServiceResult deleteItem(String name);
}
