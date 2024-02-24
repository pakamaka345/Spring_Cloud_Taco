package com.vladickgeyinc.tacocloud.web;

import com.vladickgeyinc.tacocloud.data.IngredientRepositoryJpa;
import com.vladickgeyinc.tacocloud.model.Ingredient;
import com.vladickgeyinc.tacocloud.model.Ingredient.Type;
import com.vladickgeyinc.tacocloud.model.Taco;
import com.vladickgeyinc.tacocloud.model.TacoOrder;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final IngredientRepositoryJpa ingredientRepo;
    @Autowired
    public DesignTacoController(IngredientRepositoryJpa ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        if (!ingredients.iterator().hasNext()) {
            log.warn("No ingredients found in the database");
        }
        Type[] types = Ingredient.Type.values();
        for(Type type : types)
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        List<Ingredient> ingredientList = StreamSupport.stream(ingredients.spliterator(), false).toList();
        return ingredientList.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
        if(errors.hasErrors()) return "design";

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }
}
