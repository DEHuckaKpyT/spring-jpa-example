package com.example.lesson6.service.order.argument;

import com.example.lesson6.model.NamedEntity;
import com.example.lesson6.model.Item;
import com.example.lesson6.model.Option;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Value
@Builder
public class CreateOrderArgument {

    NamedEntity customer;
    Item item;
    Set<Option> options;
}
