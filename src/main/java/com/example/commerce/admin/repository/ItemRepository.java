package com.example.commerce.admin.repository;

import com.example.commerce.admin.entity.Item;
import com.example.commerce.admin.model.ItemInput;
import com.example.commerce.member.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByName(String name);

	List<Item> findByNameContaining(String name);

}
