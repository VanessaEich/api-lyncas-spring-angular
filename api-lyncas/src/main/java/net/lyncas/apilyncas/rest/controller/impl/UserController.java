package net.lyncas.apilyncas.rest.controller.impl;

import lombok.AllArgsConstructor;
import net.lyncas.apilyncas.rest.controller.IUserController;
import net.lyncas.apilyncas.rest.dto.RequestDTO;
import net.lyncas.apilyncas.rest.dto.RequestUpdateUserDTO;
import net.lyncas.apilyncas.rest.dto.ResponseDTO;
import net.lyncas.apilyncas.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@Component
public class UserController implements IUserController {

    private final UserService service;

    @Override
    public ResponseEntity<Page<ResponseDTO>> findAll
            (@RequestParam(value= "page", defaultValue = "0") Integer page,
             @RequestParam(value= "size", defaultValue = "5") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ResponseDTO> listResponseDTO = service.findAll(pageRequest);
        return ResponseEntity.ok().body(listResponseDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> findById (@PathVariable Long id){
        ResponseDTO userDTO = service.findById(id);
        return ResponseEntity.ok().body(userDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> save (@RequestBody @Valid RequestDTO user, UriComponentsBuilder uriBuilder){
        ResponseDTO userDTO = service.save(user);
        URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(userDTO.getUserId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @Override
    public void delete (@PathVariable Long id){
        service.delete(id);
    }

    @Override
    public ResponseEntity<ResponseDTO> update (@PathVariable Long id,
                                               @RequestBody @Valid RequestUpdateUserDTO user){
        ResponseDTO userDTO = service.update(id, user);
        return ResponseEntity.ok().body(userDTO);
    }

    public ResponseEntity<List<ResponseDTO>> findByName (@RequestParam(value = "name",
            required = false, defaultValue = "") String name){
        List<ResponseDTO> listResponseDTO = service.findByName(name);
        return ResponseEntity.ok().body(listResponseDTO);
    }
}
