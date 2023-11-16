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
 *
 * @author Denis Matytsin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Option {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int additionalPrice;

//    /** Это можно добавить только когда нужно */
//    @ManyToMany(mappedBy = "options", fetch = LAZY)
//    private Set<Order> orders;
}
