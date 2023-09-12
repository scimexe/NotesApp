package com.example.Notes.note;

import com.example.Notes.exception.NoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteService {

    private final NoteRepo noteRepo;
    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public Note findNoteById(Long id){
        return noteRepo.findNoteById(id)
                .orElseThrow(()-> new NoteNotFoundException("Note by id " + id + " was not found"));
    }
    public List<Note> findAllNotes(){
        return noteRepo.findAll();
    }
    public Note addNote(Note note){

        note.setFirmaCreazione("Andrea");
        return noteRepo.save(note);
    }
    public Note updateNoteById(Long id, Note note){

        Optional<Note> noteToUpdate = noteRepo.findNoteById(id);

        noteToUpdate.get().setTitolo(note.getTitolo());
        noteToUpdate.get().setDescrizione(note.getDescrizione());
        noteToUpdate.get().setInUso(note.isInUso());

        return noteRepo.save(noteToUpdate.get());
    }
    void deleteNoteById(Long id){
        noteRepo.deleteNoteById(id);
    }
    void deleteAllNotes(){
        noteRepo.deleteAll();
    }
}
