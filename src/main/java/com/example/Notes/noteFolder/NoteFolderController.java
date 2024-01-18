package com.example.Notes.noteFolder;

import com.example.Notes.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/noteFolder")
public class NoteFolderController {

    private final NoteFolderService noteFolderService;
    public NoteFolderController(NoteFolderService noteFolderService) {this.noteFolderService = noteFolderService;

    }

    @GetMapping
    public ResponseEntity<List <NoteFolder>> getAllNoteFolders(@AuthenticationPrincipal User user){
        List<NoteFolder> noteFolders = noteFolderService.findNoteFolderByUserId(user.getId());
        return new ResponseEntity<>(noteFolders, HttpStatus.OK);
    }

    @GetMapping("/{str}")
    public ResponseEntity <List <NoteFolder>> getNoteFolder(@PathVariable("str") String str){
        List <NoteFolder> noteFolders = noteFolderService.findNoteFolderByTitle(str);

        return new ResponseEntity<>(noteFolders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<NoteFolder> addNoteFolder(@AuthenticationPrincipal User user, @RequestBody NoteFolder noteFolder){
        NoteFolder newNoteFolder = noteFolderService.addNoteFolder(user, noteFolder);
        return new ResponseEntity<>(newNoteFolder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteFolder> updateNoteFolder(@PathVariable("id") Long id, @RequestBody NoteFolder noteFolder){
        noteFolderService.updateNoteFolder(id, noteFolder);
        return new ResponseEntity<>(noteFolder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteFolderById(@PathVariable("id") Long id){
        noteFolderService.deleteNoteFolder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
