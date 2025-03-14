package kz.bitlab.spring.app.trello_app.service;

import kz.bitlab.spring.app.trello_app.model.Folder;
import kz.bitlab.spring.app.trello_app.repository.FoldersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FoldersService {
    private final FoldersRepository repository;

    public List<Folder> getAllFolders() {
        log.info("Fetching all folders");
        return repository.findAll();
    }

    public Optional<Folder> getFolderById(Long id) {
        log.info("getFolderById with ID: {}", id);
        return repository.findById(id);
    }


    public Folder createFolder(Folder folder) {
        log.info("createFolder: {}", folder.getName());
        return repository.save(folder);
    }

    public Folder updateFolder(Long id, Folder updatedFolder) {
        log.info("updateFolder with ID: {}", id);
        return repository.findById(id)
                .map(folder -> {
                    folder.setName(updatedFolder.getName());
                    return repository.save(folder);
                })
                .orElse(null);
    }

    public boolean deleteFolder(Long id) {
        log.info("deleteFolder with ID: {}", id);
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}