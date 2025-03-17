package kz.bitlab.spring.app.trello_app.controller;

import kz.bitlab.spring.app.trello_app.model.Comment;
import kz.bitlab.spring.app.trello_app.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/folders/{folderId}/tasks/{taskId}/comments")
public class CommentController {

    private final CommentsService commentsService;

    @PostMapping("/add")
    public String createComment(@PathVariable Long folderId,
                                @PathVariable Long taskId,
                                @RequestParam("comment") String commentText) {
        commentsService.createComment(commentText, taskId);
        return "redirect:/folders/" + folderId + "/tasks/" + taskId;
    }

    @PostMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long folderId,
                                @PathVariable Long taskId,
                                @PathVariable Long id) {
        commentsService.deleteComment(id);
        return "redirect:/folders/" + folderId + "/tasks/" + taskId;
    }
}