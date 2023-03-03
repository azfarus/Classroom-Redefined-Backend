package com.example.classroom.login;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private Long studentid;
    private String password;
}
