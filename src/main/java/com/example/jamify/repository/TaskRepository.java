package com.example.jamify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.jamify.model.Task;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByCompleted(boolean completed);
    List<Task> findByDescriptionContainingIgnoreCase(String description);
    long countByCompleted(boolean completed);
}