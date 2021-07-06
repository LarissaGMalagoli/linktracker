package br.com.meli.linktracker.config;

import br.com.meli.linktracker.exception.InvalidLinkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(InvalidLinkException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handler(InvalidLinkException e){
        return "Link inválido!";
    }
}
