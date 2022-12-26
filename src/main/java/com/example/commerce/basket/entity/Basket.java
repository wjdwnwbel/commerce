package com.example.commerce.basket.entity;

import com.example.commerce.admin.entity.Category;
import com.example.commerce.admin.entity.Item;
import com.example.commerce.member.entity.Member;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToOne
	@JoinColumn(name = "item_id")
	private Item item;

	private Integer amount;
	private LocalDate registerDate;
	private LocalDate updateDate;
}
