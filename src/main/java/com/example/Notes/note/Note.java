package com.example.Notes.note;

import com.example.Notes.noteFolder.NoteFolder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@SQLDelete(sql = "UPDATE note SET deleted = 1 WHERE id=?")
@Where(clause = "deleted=false")
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date modifiedAt;

    private boolean deleted = Boolean.FALSE;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "noteFolder_Id")
    public NoteFolder noteFolder;
}
