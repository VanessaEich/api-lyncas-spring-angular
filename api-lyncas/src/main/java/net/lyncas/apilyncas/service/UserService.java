package net.lyncas.apilyncas.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.lyncas.apilyncas.domain.entity.User;
import net.lyncas.apilyncas.domain.repository.IUsersRepository;
import net.lyncas.apilyncas.rest.dto.RequestDTO;
import net.lyncas.apilyncas.rest.dto.RequestUpdateUserDTO;
import net.lyncas.apilyncas.rest.dto.ResponseDTO;
import net.lyncas.apilyncas.rest.exception.LoginException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Component
public class UserService {

    private final IUsersRepository usersRepository;
    private final PasswordEncoder encoder;

    public Page<ResponseDTO> findAll(PageRequest pageRequest) {
        Page<User> users = usersRepository.findAll(pageRequest);
        usersRepository.findUsersAuthentication(users.stream().collect(Collectors.toList()));
            if (users.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Não existem usuários " +
                    "cadastrados no banco de dados");
            }
            return users.map(User::convertToResponseDTO);
    }

    public ResponseDTO findById(Long id) {
        Optional<User> user = usersRepository.findById(id);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
        return user.get().convertToResponseDTO();
    }

    public ResponseDTO save (RequestDTO user) {
        boolean exists = usersRepository.existsByLogin(user.getLogin());
        if(exists){
            throw new LoginException();
        }

        User people = user.convertToUser();
        people.getAuthentication().setPassword(encoder.encode(people.getAuthentication().getPassword()));
        return usersRepository.save(people).convertToResponseDTO();
    }

    public void delete (Long id){
        Optional<User> user = usersRepository.findById(id);
            if(user.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
            } else
                usersRepository.deleteById(id);
   }

    public ResponseDTO update (Long id, RequestUpdateUserDTO user) {
        Optional<User> people = usersRepository.findById(id);
        if(people.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        } else {
            String actualPassword;
            if(user.getPassword()==null || user.getPassword().isEmpty()){
                actualPassword= people.get().getAuthentication().getPassword();
            }else{
                actualPassword= encoder.encode(user.getPassword());
            }
            User newUser = user.convertToUser();
            newUser.setUserId(people.get().getUserId());
            newUser.setName(user.getName());
            newUser.setLastName(user.getLastName());
            newUser.setLogin(user.getLogin());
            newUser.setTelephone(user.getTelephone());
            newUser.setBirthDate(user.getBirthDate());
            newUser.getAuthentication().setAuthenticationId(people.get().getAuthentication().getAuthenticationId());
            newUser.getAuthentication().setStatus(user.getStatus());
            newUser.getAuthentication().setPassword(actualPassword);
            usersRepository.save(newUser);
            return newUser.convertToResponseDTO();
        }
    }

    public List<ResponseDTO> findByName (String name){
        List<User> users = usersRepository.findByName("%" + name + "%");
        return users.stream().map(User::convertToResponseDTO).collect(Collectors.toList());
    }
}
