package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Table(name = Role.TABLE)
public final class Role {

	public static final String TABLE = "roles";

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
	private String name;

	public Role setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return this.name;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "permission_id") })
	Set<Permission> permissions = new HashSet<>();

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Set<Permission> getPermissions() {
		return this.permissions;
	}
	
	public String toString() {
		return String.format("name:%s", this.name);
	}
}