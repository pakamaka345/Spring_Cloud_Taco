package com.vladickgeyinc.tacocloud.data;

import com.vladickgeyinc.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepositoryJpa extends CrudRepository<TacoOrder, Long> {

}
