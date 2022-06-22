package com.kaankaplan.blog_app.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedUserDto {

    private String firstName;
    private String lastName;
    private String email;
}
