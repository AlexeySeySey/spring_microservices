package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import com.example.demo.contract.DTOable;

@Entity
@Table(name=Product.TABLE)
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE = "products";
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name", unique=false, nullable=false, columnDefinition="VARCHAR(255)")
	private String name;
	
	@Column(name="image", unique=false, nullable=true, columnDefinition="VARCHAR(255)")
	private String image;
	
	@Column(name="price", unique=false, nullable=false)
	private BigDecimal price;
	
	@Column(name="created_at", unique=false, nullable=true)
	private Date createdAt;
	
	@Column(name="updated_at", unique=false, nullable=true)
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")    
	private Category category;
	
	public Category getCategory() {
		return this.category;
	}
	
	public Product setCategory(Category category) {
		this.category = category;
		return this;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Product setId(Long id) {
		this.id = id;
		return this;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}
	
	public Product setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Product setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public Product setImage(String image) {
		this.image = image;
		return this;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}
	
	public Product setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	
	public Product setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	
	public String toString () {
		return String.format("id:%s\nname:%s\nimage:%s\nprice:%s\ncreatedAt:%s\nupdatedAt:%s", this.id, this.name, this.image, this.price, this.createdAt, this.updatedAt);
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