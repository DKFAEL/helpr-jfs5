package org.soulcodeacademy.helpr.controllers.erros;

import java.time.LocalDate;

// Esta classe customiza a resposta de erro original no spring
public class CustomErrorResponse {
    private String message; // Mensagem explicando o Erro
    private Integer status; // Código de status HTTP
    private LocalDate timestamp; // Registro de data e hora em que o erro acontece
    private  String path; // Endpoint em que ocorreu o erro

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
