package br.com.treinamento.apirest.api.commom.handlers;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.treinamento.apirest.api.commom.dtos.ErrorResponse;
import br.com.treinamento.apirest.api.commom.dtos.ValidationErrorResponse;
import br.com.treinamento.apirest.core.exceptions.ModelAlreadyExistsException;
import br.com.treinamento.apirest.core.exceptions.ModelNotFoundException;


@RestControllerAdvice
public class ApiExceptionHandler {

   @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleModelNotFoundException(ModelNotFoundException exception) {
        return new ErrorResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(ModelAlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResponse handleModelAlreadyExistsException(ModelAlreadyExistsException exception) {
        return new ErrorResponse(exception.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ValidationErrorResponse.builder()
            .message("one or more fields are invalid")
            .errors(exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(
                    Collectors.groupingBy(FieldError::getField,
                    Collectors.mapping(
                        FieldError::getDefaultMessage, Collectors.toList())
                    )
                )
            )
            .build();
    }
    
}