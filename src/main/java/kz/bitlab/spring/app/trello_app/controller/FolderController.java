package kz.bitlab.spring.app.trello_app.controller;

import kz.bitlab.spring.app.trello_app.model.Folder;
import kz.bitlab.spring.app.trello_app.service.FoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/folders")
public class FolderController {
    private final FoldersService service;

    @GetMapping("/{id}")
    public String getFolderById(@PathVariable Long id, Model model) {
        service.getFolderById(id).ifPresent(folder -> model.addAttribute("folder", folder));

        return "folder-details"; 
    }
}