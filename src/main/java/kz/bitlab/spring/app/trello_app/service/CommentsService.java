package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.Comment;
import kz.bitlab.spring.app.trello_app.model.Task;
import kz.bitlab.spring.app.trello_app.repository.CommentsRepository;
import kz.bitlab.spring.app.trello_app.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final TasksRepository tasksRepository;

    public List<Comment> getAllComments() {
        log.info("getAllComments");
        return commentsRepository.findAll();
    }

    public List<Comment> getAllCommentsByTaskId(Long taskId) {
        log.info("getAllCommentsByTaskId");
        return commentsRepository.findByTaskId(taskId);
    }

    public Comment getCommentById(Long id) {
        log.info("getCommentById with ID: {}", id);
        return commentsRepository.findById(id).orElse(null);
    }

    public void createComment(String commentText, Long taskId) {
        Task task = tasksRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setTask(task);

        commentsRepository.save(comment);
    }

    public void updateComment(Long id, Comment updatedComment) {
        log.info("updateComment with ID: {}", id);
        commentsRepository.findById(id)
                .map(comment -> {
                    comment.setComment(updatedComment.getComment());
                    return commentsRepository.save(comment);
                });
    }

    public void deleteComment(Long id) {
        log.info("deleteComment with ID: {}", id);
        if (!commentsRepository.existsById(id)) {
            return;
        }
        commentsRepository.deleteById(id);
    }
}