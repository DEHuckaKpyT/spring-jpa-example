package com.example.lesson6.repository;

import com.example.lesson6.model.Task;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static util.ResourceUtils.parseJson;

/**
 * Created on 20.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
public class TaskRepositoryIT {

    @Autowired
    private TaskRepository repository;

    private final UUID taskId = UUID.fromString("00000001-0000-0000-0000-000000000001");

    @Test
    @DataSet("/datasets/repository/task/get.json")
    void get(SoftAssertions softly) {
        // Arrange
        Task expected = parseJson("/json/repository/task/get__expected.json", Task.class);

        // Act
        Optional<Task> actual = repository.findById(taskId);

        // Assert
        softly.assertThat(actual.get())
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }
}
