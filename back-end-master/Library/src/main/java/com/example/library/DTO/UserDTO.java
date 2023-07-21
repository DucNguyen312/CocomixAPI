package com.example.library.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {
    @Size(min = 3, max = 20 , message = "Invalid fullname name!(3-10 characters)")
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthdate;

    private String phoneNumber;

    private String email;

    private String username;
    @Size(min = 3, max = 15 , message = "Invalid password name!(3-10 characters)")
    private String password;
}
