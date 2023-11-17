package com.example.lesson6.service.order;

import com.example.lesson6.model.Order;
import com.example.lesson6.service.order.argument.CreateOrderArgument;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface OrderService {
    Order create(CreateOrderArgument argument);
}
