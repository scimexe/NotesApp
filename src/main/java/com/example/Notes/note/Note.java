package com.example.Notes.note;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    private boolean inUso;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dataCreazione;

    @UpdateTimestamp
    private Date dataModifica;

    @Column(updatable = false)
    private String firmaCreazione;

}
