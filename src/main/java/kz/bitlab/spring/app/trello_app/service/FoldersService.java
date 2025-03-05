package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.Folders;
import kz.bitlab.spring.app.trello_app.repository.FoldersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoldersService {
    private final FoldersRepository repository;

    public List<Folders> getAllFolders() {
        log.info("Call this method getAllFolders()");
        return repository.findAll();
    }
}
