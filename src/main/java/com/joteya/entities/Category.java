package com.joteya.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category  implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy = "category")
	@JsonIgnore
	private Collection<Product> products;

	public Category() {
		super();

	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(String name, Collection<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", products=" + products + "]";
	}
	
	

}
