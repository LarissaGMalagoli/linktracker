package br.com.meli.linktracker.config;


import br.com.meli.linktracker.exception.InvalidSenhaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class SenhaHandler {

    @ExceptionHandler(InvalidSenhaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handler(InvalidSenhaException e){
        return "Senha inválida!";
    }
}
