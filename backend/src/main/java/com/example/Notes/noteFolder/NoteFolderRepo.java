package com.example.Notes.noteFolder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteFolderRepo extends JpaRepository <NoteFolder, Long> {
    List<NoteFolder> findNoteFolderByTitleContaining(String title);

    NoteFolder findNoteFolderById(Long id);

    List<NoteFolder> findNoteFolderByUserId(Long id);
}
