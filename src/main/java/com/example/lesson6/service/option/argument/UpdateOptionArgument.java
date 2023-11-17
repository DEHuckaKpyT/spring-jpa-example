package com.example.lesson6.service.option.argument;

import lombok.Builder;
import lombok.Value;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Value
@Builder
public class UpdateOptionArgument {

    String name;
    int additionalPrice;
}
