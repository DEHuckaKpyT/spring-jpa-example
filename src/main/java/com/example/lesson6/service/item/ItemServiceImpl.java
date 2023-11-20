package com.example.lesson6.service.item;

import com.example.lesson6.model.Item;
import com.example.lesson6.model.QItem;
import com.example.lesson6.repository.ItemRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final EntityManager entityManager;

    private final QItem qItem = QItem.item;

    @Override
    @Transactional(readOnly = true)
    public Item get(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Вещь не найдена"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> list(String name, Sort sort) {
        Predicate predicate = qItem.name.containsIgnoreCase(name);

        return newArrayList(repository.findAll(predicate, sort));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> listFromJPAQuery(String name) {
        Predicate predicate = qItem.name.containsIgnoreCase(name);

        return new JPAQuery<Item>(entityManager)
                .select(qItem)
                .from(qItem)
                .where(predicate)
                .orderBy(qItem.price.desc())
                .fetch();
    }
}
