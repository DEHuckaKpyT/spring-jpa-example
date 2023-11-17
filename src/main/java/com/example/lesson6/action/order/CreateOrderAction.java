package com.example.lesson6.action.order;

import com.example.lesson6.action.order.argument.CreateOrderActionArgument;
import com.example.lesson6.model.Customer;
import com.example.lesson6.model.Item;
import com.example.lesson6.model.Option;
import com.example.lesson6.model.Order;
import com.example.lesson6.service.item.ItemService;
import com.example.lesson6.service.option.OptionService;
import com.example.lesson6.service.order.OrderService;
import com.example.lesson6.service.order.argument.CreateOrderArgument;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Component
@RequiredArgsConstructor
public class CreateOrderAction {

    private final ItemService itemService;
    private final OptionService optionService;
    private final OrderService orderService;

    @Transactional
    public Order execute(CreateOrderActionArgument argument) {
        Customer customer = getCustomer(argument.getCustomerId());
        Item item = itemService.get(argument.getItemId());
        Set<Option> options = Sets.newHashSet(optionService.get(argument.getOptionIds()));

        return orderService.create(CreateOrderArgument.builder()
                                                      .customer(customer)
                                                      .item(item)
                                                      .options(options)
                                                      .build());
    }

    private Customer getCustomer(UUID id) {
        // Обычно ФИО берётся из другого микросервиса. Сейчас это просто "заглушка"
        return Customer.builder()
                       .id(id)
                       .name("Steve")
                       .build();
    }
}
