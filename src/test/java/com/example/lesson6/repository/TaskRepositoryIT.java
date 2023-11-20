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

    /** В этом тесте можно увидеть, что поле tags вне транзакции получить нельзя. На нём висит fetch = LAZY. */
    @Test
    @DataSet("/datasets/repository/task/get.json")
    void findById(SoftAssertions softly) {
        // Arrange
        Optional<Task> task = repository.findById(taskId);

        // Act
        softly.assertThatThrownBy(() -> task.get().getTags().size())

              // Assert
              .isInstanceOf(org.hibernate.LazyInitializationException.class)
              .hasMessage("failed to lazily initialize a collection of role: com.example.lesson6.model.Task.tags, could not initialize proxy - no Session");
    }

    /**
     * В этом тесте можно увидеть, что поле attachments вне транзакции получить нельзя. Хотя, на нём висит fetch = EAGER.
     * Здесь дело в том, что в аннотации @EntityGraph(attributePaths = {"tags"}) по умолчанию тип javax.persistence.fetchgraph
     */
    @Test
    @DataSet("/datasets/repository/task/get.json")
    void findByIdWithTags(SoftAssertions softly) {
        // Arrange
        Optional<Task> task = repository.findByIdWithTags(taskId);

        // Act
        softly.assertThatThrownBy(() -> task.get().getAttachments().size())

              // Assert
              .isInstanceOf(org.hibernate.LazyInitializationException.class)
              .hasMessage("failed to lazily initialize a collection of role: com.example.lesson6.model.Task.attachments, could not initialize proxy - no Session");
    }

    /**
     * В этом тесте можно увидеть, что все поля можно получить. Поле tags вытаскивается сразу из-за @EntityGraph(attributePaths = {"tags"}...).
     * А поле attachments вытаскивается сразу из-за @ElementCollection(fetch = EAGER) и @EntityGraph(...type = LOAD)
     */
    @Test
    @DataSet("/datasets/repository/task/get.json")
    void findByIdWithTagsLoad(SoftAssertions softly) {
        // Arrange
        Task expected = parseJson("/json/repository/task/get__expected.json", Task.class);

        // Act
        Optional<Task> actual = repository.findByIdWithTagsLoad(taskId);

        // Assert
        softly.assertThat(actual.get())
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }
}
