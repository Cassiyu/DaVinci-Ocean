package br.com.fiap.oceanapi.model.dto;

import br.com.fiap.oceanapi.model.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email) {

    public static UsuarioResponse fromUsuario(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

}