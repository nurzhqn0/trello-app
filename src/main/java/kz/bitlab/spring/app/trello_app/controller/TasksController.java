package kz.bitlab.spring.app.trello_app.controller;

import kz.bitlab.spring.app.trello_app.model.Comment;
import kz.bitlab.spring.app.trello_app.model.Task;
import kz.bitlab.spring.app.trello_app.service.CommentsService;
import kz.bitlab.spring.app.trello_app.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/folders/{folderId}/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;
    private final CommentsService commentsService;

    @GetMapping("/{taskId}")
    public String getTask(Model model, @PathVariable Long taskId) {
        Task task = tasksService.getTaskById(taskId);
        List<Comment> comments = commentsService.getAllCommentsByTaskId(taskId);
        model.addAttribute("task", task);
        model.addAttribute("comments", comments);
        return "task-detailed";
    }

    @PostMapping("/add")
    public String createTask(@PathVariable Long folderId, @ModelAttribute Task task) {
        tasksService.createTask(task, folderId);
        return "redirect:/folders/" + folderId;
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        Task updatedTask = tasksService.updateTask(id, task);
        return updatedTask != null ? "redirect:/folders/" + updatedTask.getFolder().getId() : "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = tasksService.getTaskById(id);
        Long folderId = task.getFolder().getId();
        tasksService.deleteTask(id);
        return "redirect:/folders/" + folderId;
    }
}
