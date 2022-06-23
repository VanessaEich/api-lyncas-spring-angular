package net.lyncas.apilyncas.rest.controller;

import net.lyncas.apilyncas.rest.dto.RequestDTO;
import net.lyncas.apilyncas.rest.dto.RequestUpdateUserDTO;
import net.lyncas.apilyncas.rest.dto.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pessoas")
public interface IUserController {

    @GetMapping
    ResponseEntity<Page<ResponseDTO>> findAll (@RequestParam(value= "page", defaultValue = "0") Integer pagina,
                                               @RequestParam(value= "size", defaultValue = "5") Integer tamanhoPagina);

    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> findById (@PathVariable Long id);

    @PostMapping
    ResponseEntity<ResponseDTO> save (@RequestBody @Valid RequestDTO user, UriComponentsBuilder uriBuilder);

    @DeleteMapping("/{id}")
    void delete (@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<ResponseDTO> update (@PathVariable Long id, @RequestBody @Valid RequestUpdateUserDTO user);

    @GetMapping("/name")
    ResponseEntity<List<ResponseDTO>> findByName (@RequestParam(value = "name",
            required = false, defaultValue = "") String name);

}
