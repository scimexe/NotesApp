package com.example.Notes.note;

import com.example.Notes.Log.Log;
import com.example.Notes.enumeration.LOG;
import com.example.Notes.noteFolder.NoteFolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    private final NoteService noteService;
    private final NoteFolderService noteFolderService;
    public NoteController(NoteService noteService, NoteFolderService noteFolderService) {
        this.noteService = noteService;
        this.noteFolderService = noteFolderService;
    }

    @GetMapping
    public ResponseEntity<List <Note>> getAllNotes(){
        List<Note> notes = noteService.findAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/{str}")
    public ResponseEntity <List <Note>> getNote(@PathVariable("str") String str){

        boolean isLong = true;
        try{
            Long.parseLong(str);
        }
        catch (NumberFormatException e){
            isLong = false;
            Log.WriteLog(LOG.INFO, "Note with id " + str + " not found.");
        }
        finally {
            if(isLong) {
                Note note = noteService.findNoteById(Long.parseLong(str));
                return new ResponseEntity(note, HttpStatus.OK);
            }
            List<Note> notes = noteService.findNoteByTitle(str);
            if (notes.isEmpty()) {
                notes = noteService.findNoteByDesc(str);
            }
            return new ResponseEntity<>(notes, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody NoteDTO noteDTO){
        return new ResponseEntity<>(noteService.addNote(noteDTO), HttpStatus.CREATED);
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
