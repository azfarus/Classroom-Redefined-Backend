package com.example.classroom.routine;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RoutineDTO {
    private Long id;

    private Long fileId;

    public RoutineDTO(Routine r) {
        if(r == null) return ;
        this.id = r.getId();
        this.fileId = r.getFileId();
    }



}
