package kz.bitlab.spring.app.trello_app.controller;

import kz.bitlab.spring.app.trello_app.model.Folder;
import kz.bitlab.spring.app.trello_app.model.Task;
import kz.bitlab.spring.app.trello_app.model.TaskCategory;
import kz.bitlab.spring.app.trello_app.service.FoldersService;
import kz.bitlab.spring.app.trello_app.service.TaskCategoriesService;
import kz.bitlab.spring.app.trello_app.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/folders")
public class FolderController {

    private final FoldersService foldersService;
    private final TasksService tasksService;
    private final TaskCategoriesService taskCategoriesService;

    @GetMapping
    public String getAllFolders(Model model) {
        List<Folder> folders = foldersService.getAllFolders();
        model.addAttribute("folders", folders);
        return "index";
    }

    @GetMapping("/{id}")
    public String getFolderDetails(@PathVariable Long id, Model model) {
        Folder folder = foldersService.getFolderById(id).orElse(null);
        model.addAttribute("folder", folder);
        model.addAttribute("tasks", tasksService.getTasksByFolderId(id));
        model.addAttribute("categories", foldersService.getAllCategories(id));
        model.addAttribute("allCategories", taskCategoriesService.getAllTaskCategories());
        model.addAttribute("selectedCategoryId", 0);
        return "folder-detailed";
    }

    @PostMapping("/add")
    public String createFolder(@ModelAttribute Folder folder) {
        foldersService.createFolder(folder);
        return "redirect:/folders";
    }

    @PostMapping("/update/{id}")
    public String updateFolder(@PathVariable Long id, @ModelAttribute Folder folder) {
        foldersService.updateFolder(id, folder);
        return "redirect:/folders";
    }

    @PostMapping("/delete/{id}")
    public String deleteFolder(@PathVariable Long id) {
        foldersService.deleteFolder(id);
        return "redirect:/folders";
    }

    @PostMapping("/add-category/{folderId}")
    public String addCategoryToFolder(@PathVariable Long folderId, @RequestParam("categoryId") Long categoryId) {
        foldersService.addCategory(folderId, categoryId);
        return "redirect:/folders/" + folderId;
    }

    @PostMapping("/delete-category/{folderId}/{categoryId}")
    public String removeCategoryFromFolder(@PathVariable Long folderId, @PathVariable Long categoryId) {
        foldersService.deleteCategory(folderId, categoryId);
        return "redirect:/folders/" + folderId;
    }
}
