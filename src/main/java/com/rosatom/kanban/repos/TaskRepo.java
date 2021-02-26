package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Long> {
}
