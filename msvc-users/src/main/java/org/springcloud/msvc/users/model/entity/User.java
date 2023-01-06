package org.springcloud.msvc.users.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    private static final String THE_NAME_MUST_NOT_BE_EMPTY = "The name mustn't be empty";
    private static final String THE_NAME_MUST_HAVE_MINIMUM_FOUR_CHARACTERS = "The name must have minimum 4 characters";
    private static final String THE_NAME_MUST_NOT_HAVE_MORE_THAN_THEN_CHARACTERS = "The name mustn't have more than 10 characters";
    private static final String THE_EMAIL_MUST_NOT_BE_EMPTY = "The email mustn't be empty";
    private static final String THE_EMAIL_IS_NOT_CORRECT_REVIEW_AND_CORRECT_IT = "The email isn't correct, review and correct it";

    private static final String CORRECT_SYNTAX_AND_POSSIBLE_VALUES_OF_EMAIL = ".+@.+\\..+";
    private static final String THE_PASSWORD_MUST_NOT_BE_EMPTY ="The password mustn't be empty";
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = THE_NAME_MUST_NOT_BE_EMPTY)
    @Length(min = 4, message = THE_NAME_MUST_HAVE_MINIMUM_FOUR_CHARACTERS)
    @Length(max = 10, message = THE_NAME_MUST_NOT_HAVE_MORE_THAN_THEN_CHARACTERS)
    private String name;
    @Column(unique = true)
    @NotEmpty(message = THE_EMAIL_MUST_NOT_BE_EMPTY)
    @Email(message = THE_EMAIL_IS_NOT_CORRECT_REVIEW_AND_CORRECT_IT,regexp = CORRECT_SYNTAX_AND_POSSIBLE_VALUES_OF_EMAIL)
    private String email;
    @NotEmpty(message = THE_PASSWORD_MUST_NOT_BE_EMPTY)
    private String password;

}
