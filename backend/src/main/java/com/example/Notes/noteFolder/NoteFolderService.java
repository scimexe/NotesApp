package com.example.Notes.noteFolder;

import com.example.Notes.Log.Log;
import com.example.Notes.enumeration.LOG;
import com.example.Notes.exception.NotesException;
import com.example.Notes.user.User;
import com.example.Notes.user.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        //Sto cercando una cartella per ID, deve essere presente a meno che non venga eliminata nel frattempo.
        Optional <NoteFolder> noteFolder = noteFolderRepo.findById(id);
        if(noteFolder.isEmpty()){
            String msg = "Note Folder with id " + id + " not found.";
            Log.WriteLog(LOG.ERROR, msg);
            throw new NotesException(msg);
        }
        var noteFolderBuilded = NoteFolder.builder()
                .id(noteFolder.get().getId())
                .title(noteFolder.get().getTitle())
                .createdAt(noteFolder.get().getCreatedAt())
                .deleted(noteFolder.get().isDeleted())
                .notes(noteFolder.get().getNotes())
                .build();
        return noteFolderBuilded;
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

