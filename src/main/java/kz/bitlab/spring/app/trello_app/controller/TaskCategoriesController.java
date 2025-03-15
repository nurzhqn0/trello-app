package kz.bitlab.spring.app.trello_app.controller;

import kz.bitlab.spring.app.trello_app.model.Task;
import kz.bitlab.spring.app.trello_app.model.TaskCategory;
import kz.bitlab.spring.app.trello_app.service.TaskCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class TaskCategoriesController {

    private final TaskCategoriesService taskCategoriesService;

    @GetMapping
    public String getAllCategories(Model model) {
        List<TaskCategory> categories = taskCategoriesService.getAllTaskCategories();
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable Long id, Model model) {
        TaskCategory taskCategory = taskCategoriesService.getTaskCategoryById(id);
        model.addAttribute("taskCategory", taskCategory);
        return "category-details";
    }

    @PostMapping("/add")
    public String createCategory(@ModelAttribute TaskCategory category) {
        taskCategoriesService.createTaskCategory(category);
        return "redirect:/categories";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute TaskCategory category) {
        taskCategoriesService.updateTaskCategory(id, category);
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        taskCategoriesService.deleteTaskCategory(id);
        return "redirect:/categories";
    }
}
