package com.example.Notes.noteFolder;

import java.util.Date;
import com.example.Notes.note.Note;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import org.hibernate.annotations.*;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@SQLDelete(sql = "UPDATE note_folder SET deleted = 1 WHERE id=?")
@Where(clause = "deleted=false")
@Entity
public class NoteFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    private Date createdAt;

    private boolean deleted = Boolean.FALSE;
    @JsonManagedReference
    @OneToMany(cascade = ALL, mappedBy = "noteFolder", orphanRemoval = true, fetch = FetchType.LAZY)
    public List <Note> notes;
}
