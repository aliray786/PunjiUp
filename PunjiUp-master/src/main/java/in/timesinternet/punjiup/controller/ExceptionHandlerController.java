package in.timesinternet.punjiup.controller;

import in.timesinternet.punjiup.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value ={InvalidRequestBodyException.class, UserAlreadyExistException.class,
            NotFoundException.class, UnauthorizedException.class, InvalidRequestException.class})
    public ResponseEntity<HashMap<String,String>> runTimeExceptionHandler(RuntimeException runtimeException){

        return ResponseEntity.badRequest().body(new HashMap<String, String>(){{put("message",runtimeException.getMessage());}});}}

