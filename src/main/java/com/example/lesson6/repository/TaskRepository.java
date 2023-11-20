package com.example.lesson6.repository;

import com.example.lesson6.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created on 20.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {
}
