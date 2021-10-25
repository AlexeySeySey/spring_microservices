package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = Permission.TABLE)
public class Permission {

	public static final String TABLE = "permissions";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
	private String name;

	public Permission setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return this.name;
	}
}