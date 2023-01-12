package org.springcloud.msvc.courses.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DTOUser {

    private Long id;
    private String name;
    private String email;
    private String password;

}
