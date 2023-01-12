package org.springcloud.msvc.courses.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DTOCourse {
    private static final String THE_NAME_MUST_NOT_BE_EMPTY = "The name mustn't be empty";

    private Long id;
    @NotBlank(message =" " + THE_NAME_MUST_NOT_BE_EMPTY)
    private String name;
}
