package br.com.fiap.oceanapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.oceanapi.model.Usuario;
import br.com.fiap.oceanapi.model.dto.Credenciais;
import br.com.fiap.oceanapi.model.dto.Token;
import br.com.fiap.oceanapi.model.dto.UsuarioResponse;
import br.com.fiap.oceanapi.repository.UsuarioRepository;
import br.com.fiap.oceanapi.service.TokenService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Log4j2
@Tag(name = "usuario")
public class UsuarioController {

    @Autowired
    TokenService service;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    @Operation(summary = "Autenticar Usuário", description = "Autentica o usuário e gera um token de acesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<Token> login(@RequestBody Credenciais credenciais) {
        log.info(credenciais);
        authManager.authenticate(credenciais.toAuthentication());
        return ResponseEntity.ok(service.generateToken(credenciais.email()));
    }

    @PostMapping("/usuario")
    @Operation(summary = "Cadastrar Usuário", description = "Cadastra um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso")
    })
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UsuarioResponse.fromUsuario(usuario));
    }

}
