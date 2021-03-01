package com.rosatom.kanban.controller.REST.tasks_notes;

import com.rosatom.kanban.domain.Task;
import com.rosatom.kanban.dto.requests.TaskStatusRequest;
import com.rosatom.kanban.dto.responses.TaskDataResponse;
import com.rosatom.kanban.dto.responses.TaskStatusResponse;
import com.rosatom.kanban.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/table/tasks")
public class TableTasksRestController {
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    private Iterable<TaskDataResponse> getAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    private Task getTask(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PatchMapping("/{id}")
    private TaskStatusResponse setNewStatus(@PathVariable Long id, @RequestBody TaskStatusRequest newStatus) {
        return taskService.setNewStatus(id, newStatus.getStatus());
    }
}
