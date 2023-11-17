package com.example.lesson6.repository;

import com.example.lesson6.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
