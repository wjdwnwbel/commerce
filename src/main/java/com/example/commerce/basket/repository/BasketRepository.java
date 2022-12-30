package com.example.commerce.basket.repository;

import com.example.commerce.basket.entity.Basket;
import com.example.commerce.basket.model.BasketInput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	// 외래키로 조회
	Optional<Basket> findByMember_IdAndItem_Id(@Param(value = "memberID") long memberID, long ItemId);

	List<Basket> findAllByMember_Id(@Param(value = "memberID") long memberID);
	List<Basket> findAllByRegisterDateBetween(LocalDate startDate, LocalDate endDate);
}
