package net.lyncas.apilyncas.domain.entity;

import lombok.*;
import net.lyncas.apilyncas.rest.dto.RequestDTO;
import net.lyncas.apilyncas.rest.dto.ResponseDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Column(length = 14, nullable = false)
    private String telephone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private Authentication authentication;

    public ResponseDTO convertToResponseDTO() {
        return new ResponseDTO(
                this.userId,
                this.name,
                this.lastName,
                this.login,
                this.telephone,
                this.birthDate,
                this.authentication.getStatus());
    }

//    public User convertToUser (RequestDTO requestDTO) {
//        User user = new User(0L,
//                this.name = requestDTO.getName(),
//                this.lastName = requestDTO.getLastName(),
//                this.login = requestDTO.getLogin(),
//                this.telephone = requestDTO.getTelephone(),
//                this.birthDate = requestDTO.getBirthDate(),
//                new Authentication(0L,
//                        this.authentication.setPassword(requestDTO.getPassword()),
//                        this.authentication.setStatus(requestDTO.getStatus()),
//                        null)
//        );
//        user.getAuthentication().setUser(user);
//        return user;
//    }
}
