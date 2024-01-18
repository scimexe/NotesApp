package com.example.Notes.noteFolder;

import com.example.Notes.exception.NotFoundException;
import com.example.Notes.user.User;
import com.example.Notes.user.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteFolderService {

    private final NoteFolderRepo noteFolderRepo;
    private final UserRepo userRepo;
    public NoteFolderService(NoteFolderRepo noteFolderRepo, UserRepo userRepo) {
        this.noteFolderRepo = noteFolderRepo;
        this.userRepo = userRepo;
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

    public NoteFolder addNoteFolder(User user, NoteFolder newNoteFolder){
        newNoteFolder.setUser(user);
        return noteFolderRepo.save(newNoteFolder);
    }

    public NoteFolder updateNoteFolder(Long id, NoteFolder noteFolder){

        NoteFolder noteFolderToUpdate = noteFolderRepo.findNoteFolderById(id);
        return noteFolderRepo.save(noteFolderToUpdate);
    }

    public void deleteNoteFolder(Long id) {
        noteFolderRepo.deleteById(id);
    }

    public void deleteAllNoteFolder(){
        noteFolderRepo.deleteAll();
    }

    public List<NoteFolder> findNoteFolderByUserId(Long userId) {
        return noteFolderRepo.findNoteFolderByUserId(userId);
    }
}

