package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Task;
import com.rosatom.kanban.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.HashSet;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    private Task getById(Long id) {
        return taskRepo.findById(id).get();
    }

    private void create(String title, String description, GregorianCalendar start, GregorianCalendar end,
                        Account owner, Account executer) {
        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setStart(start);
        task.setEnd(end);
        task.setOwner(owner);
        task.setExecuters(new HashSet<Account>());
        task.getExecuters().add(executer);

        taskRepo.save(task);
    }

    private void delete(long id) {
        Task task = getById(id);
        taskRepo.delete(task);
    }

    private void update(Task task,String title, String description, GregorianCalendar start, GregorianCalendar end,
                        Account owner, Account executer) {
        task.setTitle(title);
        task.setDescription(description);
        task.setStart(start);
        task.setEnd(end);
        task.setOwner(owner);
        task.getExecuters().add(executer);

        taskRepo.save(task);
    }
}
