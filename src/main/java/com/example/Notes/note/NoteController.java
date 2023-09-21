package com.example.Notes.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    
    @GetMapping
    public ResponseEntity<List <Note>> getAllNotes(){
        List<Note> notes = noteService.findAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{str}")
    public ResponseEntity <List <Note>> getNote(@PathVariable("str") String str){
        List <Note> notes = noteService.findNoteByTitle(str);
        if (notes.isEmpty()) {
            notes = noteService.findNoteByDesc(str);
        }
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        Note newNote = noteService.addNote(note);
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note){
        noteService.updateNoteById(id, note);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable("id") Long id){
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllNotes(){
        noteService.deleteAllNotes();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
