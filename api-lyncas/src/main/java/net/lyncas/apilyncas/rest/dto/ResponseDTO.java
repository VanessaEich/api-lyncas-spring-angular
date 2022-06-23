package net.lyncas.apilyncas.rest.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Long userId;
    private String name;
    private String lastName;
    private String login;
    private String telephone;
    private LocalDate birthDate;
    private Boolean status;

    public ResponseDTO(ResponseDTO userDTO) {
    }
}
