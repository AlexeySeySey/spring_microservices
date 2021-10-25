package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = User.TABLE)
public final class User {

	public static final String TABLE = "users";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
	private String email;

	@Column(name = "password", nullable = false, unique = false, columnDefinition = "VARCHAR(255)")
	private String password;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	public Long getId() {
		return this.id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public Role getRole() {
		return this.role;
	}

	public User setRole(Role role) {
		this.role = role;
		return this;
	}

	@Override
	public String toString() {
		return String.format("id:%s\nemail:%s\npassword:%s\nrole:%s", this.id, this.email, this.password, this.role);
	}
}