package com.example.commerce.admin.repository;

import com.example.commerce.admin.entity.Category;
import com.example.commerce.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findByName(String name);
}
