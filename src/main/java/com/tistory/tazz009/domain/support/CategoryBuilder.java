package com.tistory.tazz009.domain.support;

import org.springframework.stereotype.Component;

import com.tistory.tazz009.domain.Category;

@Component
public class CategoryBuilder extends EntityBuilder<Category> {

	@Override
	void initProduct() {
	}

	@Override
	Category assembleProduct() {
		return this.product;
	}

	public CategoryBuilder name(String name) {
		this.product = new Category(name);
		return this;
	}

}

