package com.vladickgeyinc.tacocloud.web;

import com.vladickgeyinc.tacocloud.data.OrderRepositoryJpa;
import com.vladickgeyinc.tacocloud.model.TacoOrder;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepositoryJpa orderRepo;

    public OrderController(OrderRepositoryJpa orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()) return "orderForm";
        order.setPlacedAt(new Date());
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
