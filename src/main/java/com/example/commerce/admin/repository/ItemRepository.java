package com.example.commerce.admin.repository;

import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.model.ItemInput;
import com.example.commerce.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	Optional<Item> findByName(String name);
}
