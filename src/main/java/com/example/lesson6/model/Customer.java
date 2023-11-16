package com.example.lesson6.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.UUID;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Data
@Builder
@Embeddable
public class Customer {

    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false)
    private String customerName;
}
