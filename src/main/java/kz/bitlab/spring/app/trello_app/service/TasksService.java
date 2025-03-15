package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.Folder;
import kz.bitlab.spring.app.trello_app.model.Task;
import kz.bitlab.spring.app.trello_app.repository.FoldersRepository;
import kz.bitlab.spring.app.trello_app.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final FoldersRepository foldersRepository;


    public Task createTask(Task task, Long folderId) {
        log.info("createTask Task: {}", task.getTitle());
        Folder folder = foldersRepository.findById(folderId).orElse(null);

        if (folder == null) {
            log.warn("Folder not found with ID: {}", folderId);
            return null;
        }

        task.setFolder(folder);
        return tasksRepository.save(task);
    }

    public List<Task> getAllTasks() {
        log.info("getAllTasks");
        return tasksRepository.findAll();
    }

    public Task getTaskById(Long id) {
        log.info("getTaskById Task with ID: {}", id);
        return tasksRepository.findById(id).orElse(null);
    }

    public List<Task> getTasksByFolderId(Long folderId) {
        return tasksRepository.findByFolderId(folderId);
    }

    public Task updateTask(Long id, Task updatedTask) {
        log.info("updateTaskCategory with ID: {}", id);
        return tasksRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    return tasksRepository.save(task);
                })
                .orElse(null);
    }

    public boolean deleteTask(Long id) {
        log.info("deleteTaskCategory with ID: {}", id);
        if (!tasksRepository.existsById(id)) {
            return false;
        }
        tasksRepository.deleteById(id);
        return true;
    }
}
