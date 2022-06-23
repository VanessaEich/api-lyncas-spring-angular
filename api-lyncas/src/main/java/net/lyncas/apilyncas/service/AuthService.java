package net.lyncas.apilyncas.service;

import lombok.RequiredArgsConstructor;
import net.lyncas.apilyncas.domain.entity.User;
import net.lyncas.apilyncas.domain.repository.IUsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Vanessa Eich
 * @created 13/05/2022
 */
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

        private final IUsersRepository repository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user = repository.findByLogin(username);
            if(user.isEmpty()){
                throw new UsernameNotFoundException("Usuário não encontrado");
            }
            return new UserDetailsData(user);
        }
}
