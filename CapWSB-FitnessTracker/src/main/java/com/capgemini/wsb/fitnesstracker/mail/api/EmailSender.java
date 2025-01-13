package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;

/**
 * Interfejs API dla komponentu odpowiedzialnego za wysyłanie wiadomości e-mail.
 */
public interface EmailSender {

    /**
     * Wysyła wiadomość e-mail do odbiorcy na podstawie dostarczonych danych z {@link EmailDto}.
     *
     * @param email informacje o wiadomości e-mail do wysłania
     */
    void send(EmailDto email);

}
