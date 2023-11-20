package com.example.lesson6.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created on 16.11.2023.
 * <p>
 * Товар/услуга
 *
 * @author Denis Matytsin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

//    /** Это можно добавить только когда нужно */
//    @OneToMany(mappedBy = "item", fetch = LAZY)
//    private Set<Order> orders;
}
