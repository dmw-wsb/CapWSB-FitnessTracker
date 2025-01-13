package com.capgemini.wsb.fitnesstracker.mail.api;

/**
 * Obiekt transferu danych (DTO) dla wiadomości e-mail.
 * Zawiera podstawowe informacje dotyczące e-maila, takie jak adres odbiorcy,
 * temat wiadomości oraz jej treść.
 */
public record EmailDto(String toAddress, String subject, String content) {
}
