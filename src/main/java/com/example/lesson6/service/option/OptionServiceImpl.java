package com.example.lesson6.service.option;

import com.example.lesson6.model.Option;
import com.example.lesson6.repository.OptionRepository;
import com.example.lesson6.service.option.argument.UpdateOptionArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Option get(UUID id) {
        return repository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Опция не найдена"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Option> get(Set<UUID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @Transactional
    public Option update(UUID id, UpdateOptionArgument argument) {
        Option option = get(id);

        option.setName(argument.getName());
        option.setAdditionalPrice(argument.getAdditionalPrice());

        return repository.save(option);
    }
}
