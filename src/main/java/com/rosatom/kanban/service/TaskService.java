package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Note;
import com.rosatom.kanban.domain.Task;
import com.rosatom.kanban.domain.TaskStatus;
import com.rosatom.kanban.dto.requests.TaskColorRequest;
import com.rosatom.kanban.dto.responses.NoteDataResponse;
import com.rosatom.kanban.dto.responses.TaskColorResponse;
import com.rosatom.kanban.dto.responses.TaskDataResponse;
import com.rosatom.kanban.dto.responses.TaskStatusResponse;
import com.rosatom.kanban.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    private Task getById(Long id) {
        return taskRepo.findById(id).get();
    }

//    private void create(String title, String description, GregorianCalendar start, GregorianCalendar end,
//                        Account owner, Account executer) {
//        Task task = new Task();
//
//        task.setTitle(title);
//        task.setDescription(description);
//        task.setStartDate(start);
//        task.setEndDate(end);
//        task.setOwner(owner);
//        task.setExecuters(new HashSet<Account>());
//        task.getExecuters().add(executer);
//
//        taskRepo.save(task);
//    }

    private void delete(long id) {
        Task task = getById(id);
        taskRepo.delete(task);
    }

//    private void update(Task task,String title, String description, GregorianCalendar start, GregorianCalendar end,
//                        Account owner, Account executer) {
//        task.setTitle(title);
//        task.setDescription(description);
//        task.setStartDate(start);
//        task.setEndDate(end);
//        task.setOwner(owner);
//        task.getExecuters().add(executer);
//
//        taskRepo.save(task);
//    }

    public Iterable<TaskDataResponse> findAll() {
        List<TaskDataResponse> data = new ArrayList<>();
        for (Task note: taskRepo.findAll()) {
            data.add(new TaskDataResponse(
                    note.getId(),
                    note.getStatus(),
                    note.getTitle(),
                    note.getDescription(),
                    note.getStartDate(),
                    note.getEndDate()
            ));
        }
        return data;
    }

    public Task findById(Long id) {
        return taskRepo.findById(id).get();
    }

    public TaskStatusResponse setNewStatus(Long id, TaskStatus newStatus) {
        Task task = findById(id);
        if (task != null) {
            task.setStatus(newStatus);
            taskRepo.save(task);
            return new TaskStatusResponse(id, HttpStatus.OK);
        } else {
            return new TaskStatusResponse(id, HttpStatus.BAD_REQUEST);
        }
    }

    public TaskColorResponse changeColor(long id, TaskColorRequest request) {
        Task task = findById(id);
        task.setColor(request.getColor());
        taskRepo.save(task);
        return new TaskColorResponse(id, HttpStatus.OK);
    }

    public List<TaskDataResponse> filterAll() {
        List<Task> tasks = taskRepo.findAll().stream()
                .sorted(Comparator.comparing(Task::getColor))
                .collect(Collectors.toList());
        List<TaskDataResponse> result = new ArrayList<TaskDataResponse>();
        for (Task task: tasks) {
            result.add(new TaskDataResponse(
                    task.getId(),
                    task.getStatus(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStartDate(),
                    task.getEndDate()
            ));
        }
        return result;
    }
}