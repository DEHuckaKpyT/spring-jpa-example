package com.example.lesson6.service.option;

import com.example.lesson6.model.Option;
import com.example.lesson6.service.option.argument.UpdateOptionArgument;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface OptionService {

    Option get(UUID id);

    List<Option> get(Set<UUID> ids);

    Option update(UUID id, UpdateOptionArgument argument);
}
