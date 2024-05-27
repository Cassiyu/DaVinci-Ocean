package br.com.fiap.oceanapi.validation;

public record RestValidationError(String field, String message) {
}