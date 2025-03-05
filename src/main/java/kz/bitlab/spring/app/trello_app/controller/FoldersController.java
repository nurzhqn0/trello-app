package kz.bitlab.spring.app.trello_app.controller;

import kz.bitlab.spring.app.trello_app.service.FoldersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/folders")
public class FoldersController {
    private final FoldersService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("folders", service.getAllFolders());
        return "index";
    }
}
