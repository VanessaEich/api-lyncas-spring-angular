package net.lyncas.apilyncas.domain.dataseed;

import lombok.RequiredArgsConstructor;
import net.lyncas.apilyncas.domain.entity.Authentication;
import net.lyncas.apilyncas.domain.entity.User;
import net.lyncas.apilyncas.domain.repository.IUsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Essa classe cria um usuário padrão no banco de dados, se o banco estiver vazio ao iniciar o programa
 * @author Vanessa Eich
 * @created 28/04/2022
 */

@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final IUsersRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user = new User(1L, "Administrador", "Sistema",
                    "admin@lyncas.net", "47999999999", LocalDate.of(2000,01,01),
                    new Authentication(1L,encoder.encode(
                            "senhaboa1"), true));
            user.getAuthentication().setUser(user);
            userRepository.save(user);
        }
    }
}
