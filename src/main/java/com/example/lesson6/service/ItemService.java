package com.example.lesson6.service;

import com.example.lesson6.model.Item;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface ItemService {
    List<Item> list(String name, Sort sort);

    List<Item> listFromQuerydsl(String name);
}
