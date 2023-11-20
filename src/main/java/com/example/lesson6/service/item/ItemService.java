package com.example.lesson6.service.item;

import com.example.lesson6.model.Item;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface ItemService {
    Item get(UUID id);

    List<Item> list(String name, Sort sort);

    List<Item> listFromJPAQuery(String name);
}
