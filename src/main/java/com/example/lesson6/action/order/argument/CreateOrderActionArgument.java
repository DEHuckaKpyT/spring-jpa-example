package com.example.lesson6.action.order.argument;

import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Value
@Builder
public class CreateOrderActionArgument {

    UUID customerId;
    UUID itemId;
    Set<UUID> optionIds;
}
