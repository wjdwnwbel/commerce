package com.example.commerce.member.entity;

import com.example.commerce.admin.entity.Item;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice.Local;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = " member_id")
	private Member memberId;

	@ManyToOne
	@JoinColumn(name = " product_id")
	private Item itemId;

	private String status;
	private LocalDateTime registerDate;
	private LocalDateTime cancelDate;



}
