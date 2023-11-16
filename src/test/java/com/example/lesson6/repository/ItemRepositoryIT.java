package com.example.lesson6.repository;

import com.example.lesson6.model.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import util.ResourceUtils;

import java.util.List;

/**
 * Created on 16.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
public class ItemRepositoryIT {

    @Autowired
    private ItemRepository repository;

    private static final String searchItemName = "РАСКА";

    /** с помощью query method */
    @Test
    @DataSet("/datasets/repository/item/list-by-name.json")
    void getByQueryMethod(SoftAssertions softly) {
        // Arrange
        List<Item> expected = ResourceUtils.parseJson("/json/repository/item/list-by-name__expected.json", new TypeReference<>() {});

        // Act
        List<Item> actual = repository.findByNameContainingIgnoreCaseOrderByPriceDesc(searchItemName);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }

    /** с помощью @Query */
    @Test
    @DataSet("/datasets/repository/item/list-by-name.json")
    void getByQueryAnnotation(SoftAssertions softly) {
        // Arrange
        List<Item> expected = ResourceUtils.parseJson("/json/repository/item/list-by-name__expected.json", new TypeReference<>() {});

        // Act
        List<Item> actual = repository.findListByName(searchItemName);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }

    /** с помощью native @Query */
    @Test
    @DataSet("/datasets/repository/item/list-by-name.json")
    void getByQueryAnnotationNativeTrue(SoftAssertions softly) {
        // Arrange
        List<Item> expected = ResourceUtils.parseJson("/json/repository/item/list-by-name__expected.json", new TypeReference<>() {});

        // Act
        List<Item> actual = repository.findListByNameNative(searchItemName);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }
}
