package com.example.Notes.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
    Optional <Note> findNoteById(Long id);
    
    void deleteNoteById(Long id);
}

