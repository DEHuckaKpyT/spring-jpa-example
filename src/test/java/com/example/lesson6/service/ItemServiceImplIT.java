package com.example.lesson6.service;

import com.example.lesson6.model.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import util.ResourceUtils;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
public class ItemServiceImplIT {

    @Autowired
    private ItemService service;

    private static final String searchItemName = "РАСКА";

    /** с помощью jpa repository */
    @Test
    @DataSet("/datasets/service/item/list-by-name.json")
    void getByRepository(SoftAssertions softly) {
        // Arrange
        Sort sort = Sort.by(DESC, "price");
        List<Item> expected = ResourceUtils.parseJson("/json/service/item/list-by-name__expected.json", new TypeReference<>() {});

        // Act
        List<Item> actual = service.list(searchItemName, sort);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }

    /** с помощью QueryDSL */
    @Test
    @DataSet("/datasets/service/item/list-by-name.json")
    void getByQuerydsl(SoftAssertions softly) {
        // Arrange
        List<Item> expected = ResourceUtils.parseJson("/json/service/item/list-by-name__expected.json", new TypeReference<>() {});

        // Act
        List<Item> actual = service.listFromQuerydsl(searchItemName);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }
}
