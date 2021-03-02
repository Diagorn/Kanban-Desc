package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findAll();
}
