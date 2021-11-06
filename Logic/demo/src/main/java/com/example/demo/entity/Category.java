package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name=Category.TABLE)
public class Category {
	
	public static final String TABLE = "categories";
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name", unique=false, nullable=false, columnDefinition="VARCHAR(255)")
	private String name;
	
	@Column(name="created_at", unique=false, nullable=true)
	private Date createdAt;
	
	@Column(name="updated_at", unique=false, nullable=true)
	private Date updatedAt;
	
	@OneToMany(targetEntity = Product.class, mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)    
	private List<Product> products = new ArrayList<>();
	
	public List<Product> getProducts() {
		return this.products;
	}
	
	public Category setProducts(List<Product> products) {
		this.products = products;
		return this;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Category setId(Long id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Category setName(String name) {
		this.name = name;
		return this;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	public Category setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	
	public Category setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	
	public String toString () {
		return String.format("id:%s\nname:%s\ncreatedAt:%s\nupdatedAt:%s", this.id, this.name, this.createdAt, this.updatedAt);
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.updatedAt = new Date();
	}
	
	@PrePersist
	protected void prePersist() {
		if (this.createdAt == null) this.createdAt = new Date();
		if (this.updatedAt == null) this.updatedAt = new Date();
	}
}