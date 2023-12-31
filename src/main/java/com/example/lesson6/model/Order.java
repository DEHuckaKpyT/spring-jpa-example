package com.example.lesson6.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

/**
 * Created on 16.11.2023.
 * <p>
 * Заказ клиента
 *
 * @author Denis Matytsin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item_order")
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    /** Клиент/заказчик */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "customer_id", nullable = false)),
            @AttributeOverride(name = "name", column = @Column(name = "customer_name", nullable = false))
    })
    private NamedEntity customer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Item item;

    @Column(nullable = false)
    @Enumerated(value = STRING)
    private OrderStatus status;

    /** Л=Дополнительные выбранные опции */
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "order_id"),
               inverseJoinColumns = @JoinColumn(name = "option_id"))
    private Set<Option> options;

    @Column(nullable = false)
    private LocalDateTime createDate;
}
