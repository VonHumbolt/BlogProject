package com.kaankaplan.blog_app.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditPostDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String content;
}
