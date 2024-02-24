package com.vladickgeyinc.tacocloud.web;


import com.vladickgeyinc.tacocloud.data.IngredientRepositoryJpa;
import com.vladickgeyinc.tacocloud.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private IngredientRepositoryJpa ingredientRepo;
    @Autowired
    public IngredientByIdConverter(IngredientRepositoryJpa ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }
    @Override
    public Ingredient convert(String id){
        return ingredientRepo.findById(id).orElse(null);
    }
}
