package org.soulcodeacademy.helpr.controllers.erros;

//Quando acontece um erro, esta classe decide como retornar
//a mensagem para o Cliente

import org.soulcodeacademy.helpr.services.erros.ParametrosInsuficientesError;
import org.soulcodeacademy.helpr.services.erros.RecursoNaoEncontradoError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice // capacidade de gerenciar os erros que acontecem
public class ResourceExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoError.class)
    public ResponseEntity<CustomErrorResponse> recursoNaoEncontradoError(RecursoNaoEncontradoError error, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();
        response.setTimestamp(LocalDate.now()); // Define a data e hora em que o erro ocorreu
        response.setStatus(HttpStatus.NOT_FOUND.value()); // Define como 404 o  codigo de status
        response.setMessage(error.getMessage());// Define a mensagem de erro vinda do service
        response.setPath(request.getRequestURI()); // Define qual endpoint ocorreu a requisição

        //Retorna o objeto com os dados
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ParametrosInsuficientesError.class)
    public  ResponseEntity<CustomErrorResponse> parametrosInsuficientesError(ParametrosInsuficientesError error, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDate.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(error.getMessage());
        response.setPath(request.getRequestURI());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler (BadCredentialsException.class)
    public  ResponseEntity<CustomErrorResponse> badCredentialsException(BadCredentialsException erro, HttpServletRequest request){
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDate.now());
        response.setStatus(HttpStatus.FORBIDDEN.value());// 403
        response.setMessage("Email/senha inválidos!");
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
}
