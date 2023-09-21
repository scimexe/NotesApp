package com.example.Notes.noteFolder;

import com.example.Notes.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteFolderService {

    private final NoteFolderRepo noteFolderRepo;

    public NoteFolderService(NoteFolderRepo noteFolderRepo) {
        this.noteFolderRepo = noteFolderRepo;
    }

    public NoteFolder findNoteFolderById(Long id){
        return noteFolderRepo.findById(id).orElseThrow(()-> new NotFoundException("Note folder by id " + id + " was not found."));
    }

    public List<NoteFolder> findNoteFolderByTitle(String title){
        return noteFolderRepo.findNoteFolderByTitleContaining(title);
    }

    public List<NoteFolder> findAllNoteFolders() {
        return noteFolderRepo.findAll();
    }

    public NoteFolder addNoteFolder(NoteFolder newNoteFolder){
        return noteFolderRepo.save(newNoteFolder);
    }

    public NoteFolder updateNoteFolder(Long id, NoteFolder noteFolder){

        Optional<NoteFolder> noteFolderToUpdate = noteFolderRepo.findNoteFolderById(id);
        return noteFolderRepo.save(noteFolderToUpdate.get());
    }

    public void deleteNoteFolder(Long id) {
        noteFolderRepo.deleteById(id);
    }

    public void deleteAllNoteFolder(){
        noteFolderRepo.deleteAll();
    }
}
