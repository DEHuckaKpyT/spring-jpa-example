package com.example.lesson6.repository;

import com.example.lesson6.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface ItemRepository extends JpaRepository<Item, UUID>, QuerydslPredicateExecutor<Item> {

    /**
     * Query Method
     * <p>
     * <a href="https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html">spring query methods</a>
     */
    List<Item> findByNameContainingIgnoreCaseOrderByPriceDesc(String name);

    @Query("select i from Item i where lower(i.name) like lower(concat('%', :name,'%')) order by i.price desc")
    List<Item> findListByName(@Param("name") String name);

    @Query(value = "select * from item where name ~* :name order by price desc", nativeQuery = true)
    List<Item> findListByNameNative(@Param("name") String name);
}
