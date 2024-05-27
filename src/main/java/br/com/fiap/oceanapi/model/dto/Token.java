package br.com.fiap.oceanapi.model.dto;

public record Token(
        String token,
        String type,
        String prefix) {
}