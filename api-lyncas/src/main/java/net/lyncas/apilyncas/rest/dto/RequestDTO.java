package net.lyncas.apilyncas.rest.dto;

import lombok.*;
import net.lyncas.apilyncas.domain.entity.Authentication;
import net.lyncas.apilyncas.domain.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Length(min = 3, max = 50, message = "{campo.nome.invalido}")
    private String name;

    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    @Length(min = 3, max = 50, message = "{campo.sobrenome.invalido}")
    private String lastName;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    @Email(regexp = ".+[@].+[\\.].+", message = "{campo.email.invalido}")
    private String login;

    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    @Pattern(regexp = "[0-9]{2}([9]{1})?([0-9]{4})([0-9]{4})$", message = "{campo.telefone.invalido}")
    private String telephone;

    @NotNull(message = "{campo.dataNascimento.obrigatorio}")
    private LocalDate birthDate;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{6,}$", message = "{campo.senha.invalido}")
    private String password;

    private Boolean status;

    public User convertToUser () {
        User user = new User(0L,
                this.name,
                this.lastName,
                this.login,
                this.telephone,
                this.birthDate,
                new Authentication(
                        this.password,
                        this.status,
                        null)
        );
        user.getAuthentication().setUser(user);
        return user;
    }
}
