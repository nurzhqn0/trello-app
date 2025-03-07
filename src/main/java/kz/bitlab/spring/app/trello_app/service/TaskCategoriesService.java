package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.TaskCategory;
import kz.bitlab.spring.app.trello_app.repository.TaskCategoriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskCategoriesService {
    private final TaskCategoriesRepository repository;

    public TaskCategory createTaskCategory(TaskCategory category) {
        log.info("create TaskCategory: {}", category.getName());
        return repository.save(category);
    }

    public List<TaskCategory> getAllTaskCategories() {
        log.info("getAllTaskCategories TaskCategories");
        return repository.findAll();
    }

    public TaskCategory getTaskCategoryById(Long id) {
        log.info("getTaskCategoryById TaskCategory with ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    public TaskCategory updateTaskCategory(Long id, TaskCategory updatedCategory) {
        log.info("updateTaskCategory with ID: {}", id);
        return repository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return repository.save(category);
                })
                .orElse(null);
    }

    public boolean deleteTaskCategory(Long id) {
        log.info("deleteTaskCategory with ID: {}", id);
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}