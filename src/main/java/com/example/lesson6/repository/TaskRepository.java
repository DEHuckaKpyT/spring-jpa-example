package com.example.lesson6.repository;

import com.example.lesson6.model.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

/**
 * Created on 20.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    /** Вытягивание тегов сразу с помощью <a href="https://www.baeldung.com/spring-data-jpa-named-entity-graphs">@EntityGraph</a> */
    @EntityGraph(attributePaths = {"tags"})
    @Query("select t from Task t where id = :id")
    Optional<Task> findByIdWithTags(@Param("id") UUID id);

    @EntityGraph(attributePaths = {"tags"}, type = LOAD)
    @Query("select t from Task t where id = :id")
    Optional<Task> findByIdWithTagsLoad(@Param("id") UUID id);
}
