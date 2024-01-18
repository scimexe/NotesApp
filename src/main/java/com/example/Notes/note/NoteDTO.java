package com.example.Notes.note;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoteDTO {
    private String title;
    private String description;
    private Long folderId;
}
