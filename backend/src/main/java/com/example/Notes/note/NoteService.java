package com.example.Notes.note;

import com.example.Notes.exception.NotFoundException;
import com.example.Notes.noteFolder.NoteFolderRepo;
import com.example.Notes.noteFolder.NoteFolderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    private final NoteRepo noteRepo;
    private final NoteFolderService noteFolderService;
    public NoteService(NoteRepo noteRepo, NoteFolderService noteFolderService) {
        this.noteRepo = noteRepo;
        this.noteFolderService = noteFolderService;
    }

    public Note findNoteById(Long id){
        return noteRepo.findNoteById(id).orElseThrow(()-> new NotFoundException("Note by id " + id + " was not found."));
    }

    public List <Note> findNoteByDesc(String description){
        return noteRepo.findNoteByDescriptionContaining(description);
    }

    public List<Note> findNoteByTitle(String title) {
        return noteRepo.findNoteByTitleContaining(title);
    }

    public List<Note> findAllNotes(){
        return noteRepo.findAll();
    }

    public Note addNote(NoteDTO newNote) {
        Note note = new Note();
        note.setTitle(newNote.getTitle());
        note.setDescription(newNote.getDescription());
        note.setNoteFolder(noteFolderService.findNoteFolderById(newNote.getFolderId()));
        return noteRepo.save(note);
    }

    public Note updateNoteById(Long id, Note note){

        Optional<Note> noteToUpdate = noteRepo.findNoteById(id);
        noteToUpdate.get().setTitle(note.getTitle());
        noteToUpdate.get().setDescription(note.getDescription());
        return noteRepo.save(noteToUpdate.get());
    }

    void deleteNoteById(Long id){
        noteRepo.deleteNoteById(id);
    }

    void deleteAllNotes(){
        noteRepo.deleteAll();
    }
}
