package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.TaskCategory;
import kz.bitlab.spring.app.trello_app.repository.TaskCategoriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskCategoriesService {
    private final TaskCategoriesRepository repository;

    public void createTaskCategory(TaskCategory category) {
        log.info("create TaskCategory: {}", category.getName());
        repository.save(category);
    }

    public List<TaskCategory> getAllTaskCategories() {
        log.info("getAllTaskCategories TaskCategories");
        return repository.findAll();
    }

    public TaskCategory getTaskCategoryById(Long id) {
        log.info("getTaskCategoryById TaskCategory with ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    public void updateTaskCategory(Long id, TaskCategory updatedCategory) {
        log.info("updateTaskCategory with ID: {}", id);
        repository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return repository.save(category);
                });
    }

    public void deleteTaskCategory(Long id) {
        log.info("deleteTaskCategory with ID: {}", id);
        if (!repository.existsById(id)) {
            return;
        }
        repository.deleteById(id);
    }
}