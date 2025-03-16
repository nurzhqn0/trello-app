package kz.bitlab.spring.app.trello_app.service;

import jakarta.transaction.Transactional;
import kz.bitlab.spring.app.trello_app.model.Folder;
import kz.bitlab.spring.app.trello_app.model.TaskCategory;
import kz.bitlab.spring.app.trello_app.repository.FoldersRepository;
import kz.bitlab.spring.app.trello_app.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoldersService {
    private final FoldersRepository repository;
    private final TasksRepository tasksRepository;
    private final TaskCategoriesService taskCategoriesService;
    private final FoldersRepository foldersRepository;

    public List<Folder> getAllFolders() {
        log.info("Fetching all folders");
        return repository.findAll();
    }

    public Optional<Folder> getFolderById(Long id) {
        log.info("getFolderById with ID: {}", id);
        return repository.findById(id);
    }


    public void createFolder(Folder folder) {
        log.info("createFolder: {}", folder.getName());
        repository.save(folder);
    }

    public void updateFolder(Long id, Folder updatedFolder) {
        log.info("updateFolder with ID: {}", id);
        repository.findById(id)
                .map(folder -> {
                    folder.setName(updatedFolder.getName());
                    return repository.save(folder);
                });
    }

    @Transactional
    public void deleteFolder(Long id) {
        log.info("deleteFolder with ID: {}", id);
        Folder folder = repository.findById(id).orElse(null);
        if (folder != null) {
            tasksRepository.setFolderToNullByFolderId(id);
            repository.deleteById(id);
        }
    }

    @Transactional
    public Set<TaskCategory> getAllCategories(Long id) {
        log.info("getAllCategories with ID: {}", id);
        Folder folder = repository.findById(id).orElse(null);
        if (folder != null) {
            return Optional.ofNullable(folder.getCategories()).orElse(new HashSet<>());
        }
        return new HashSet<>();
    }

    @Transactional
    public void addCategory(Long id, Long categoryId) {
        log.info("assignCategoriesToFolder with ID: {}", id);
        Folder folder = repository.findById(id).orElse(null);
        if (folder != null) {
            Set<TaskCategory> folderCategories = new HashSet<>(Optional.ofNullable(folder.getCategories()).orElse(new HashSet<>()));
            TaskCategory categoryToAdd = taskCategoriesService.getTaskCategoryById(categoryId);
            if (!folderCategories.contains(categoryToAdd)) {
                folderCategories.add(categoryToAdd);
                folder.setCategories(folderCategories);
            }
        }
    }

    @Transactional
    public void deleteCategory(Long id, Long categoryId) {
        log.info("deleteCategory with ID: {}", id);
        Folder folder = repository.findById(id).orElse(null);
        if (folder != null) {
            Set<TaskCategory> folderCategories = new HashSet<>(Optional.ofNullable(folder.getCategories()).orElse(new HashSet<>()));
            folderCategories.remove(taskCategoriesService.getTaskCategoryById(categoryId));
            folder.setCategories(folderCategories);
        }
    }
}