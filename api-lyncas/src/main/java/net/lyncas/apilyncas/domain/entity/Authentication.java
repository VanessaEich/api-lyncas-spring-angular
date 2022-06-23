package net.lyncas.apilyncas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="authentication")
public class Authentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authenticationId;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name= "userId")
    private User user;

    public Authentication(String password, Boolean status, User user) {
        this.password = password;
        this.status = status;
        this.user = user;
    }

    public Authentication(Long authenticationId, String password, boolean status) {
        this.authenticationId = authenticationId;
        this.password = password;
        this.status = status;
    }
}
