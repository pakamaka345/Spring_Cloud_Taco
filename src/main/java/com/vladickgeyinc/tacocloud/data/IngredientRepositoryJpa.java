package com.vladickgeyinc.tacocloud.data;

import com.vladickgeyinc.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepositoryJpa extends CrudRepository<Ingredient, String> {
}
