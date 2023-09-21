package com.example.Notes.noteFolder;

import java.util.Date;
import com.example.Notes.note.Note;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class NoteFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(cascade = ALL, mappedBy = "noteFolder", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List <Note> notes;
}
