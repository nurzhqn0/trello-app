package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.Comment;
import kz.bitlab.spring.app.trello_app.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;

    public List<Comment> getAllComments() {
        log.info("getAllComments");
        return commentsRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        log.info("getCommentById with ID: {}", id);
        return commentsRepository.findById(id).orElse(null);
    }

    public Comment createComment(Comment comment) {
        log.info("createComment comment: {}", comment.getComment());
        return commentsRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        log.info("updateComment with ID: {}", id);
        return commentsRepository.findById(id)
                .map(comment -> {
                    comment.setComment(updatedComment.getComment());
                    return commentsRepository.save(comment);
                })
                .orElse(null);
    }

    public boolean deleteComment(Long id) {
        log.info("deleteComment with ID: {}", id);
        if (!commentsRepository.existsById(id)) {
            return false;
        }
        commentsRepository.deleteById(id);
        return true;
    }
}