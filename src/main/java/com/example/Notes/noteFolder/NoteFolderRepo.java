package com.example.Notes.noteFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteFolderRepo extends JpaRepository <NoteFolder, Long> {
    List<NoteFolder> findNoteFolderByTitleContaining(String title);

    Optional<NoteFolder> findNoteFolderById(Long id);
}
