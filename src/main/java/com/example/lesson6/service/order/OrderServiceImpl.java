package com.example.lesson6.service.order;

import com.example.lesson6.model.Order;
import com.example.lesson6.repository.OrderRepository;
import com.example.lesson6.service.order.argument.CreateOrderArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.example.lesson6.model.OrderStatus.DRAFT;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    @Transactional
    public Order create(CreateOrderArgument argument) {
        return repository.save(Order.builder()
                                    .customer(argument.getCustomer())
                                    .item(argument.getItem())
                                    .status(DRAFT)
                                    .options(argument.getOptions())
                                    .createDate(LocalDateTime.now())
                                    .build());
    }
}
