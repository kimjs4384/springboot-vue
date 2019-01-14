package com.springboot.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Customer {
	
	/*
	 * @id primary key 설정
	 * @SequenceGenerator
	 * @@GeneratedValue                                                          
	 */
	
	@Id
	@SequenceGenerator(
		name="customer_id_seq_generator", // 제너레이터 명
		sequenceName="customer_id_seq", // 실제 시퀀스 명 
		initialValue=1, // 시작값 
		allocationSize=1 
	)
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE, // AUTO, TABLE, IDENTITY, SEQUENCE
		generator="customer_id_seq_generator" // 제너레이터 명
	)
	private Long id;
	
	// @Column
	@Column
	private String name;
	
	@Column
	private int age;
	
	@Column
	private boolean active;
	
	public Customer() {}
	
	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
		this.active = false;
	}
	
}
