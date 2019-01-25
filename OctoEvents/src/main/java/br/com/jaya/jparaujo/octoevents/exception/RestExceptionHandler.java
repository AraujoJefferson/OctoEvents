package br.com.jaya.jparaujo.octoevents.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger loggerREH = LogManager.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        loggerREH.error("handleHttpMessageNotReadable");
        return new ResponseEntity<>("400", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        loggerREH.error("handleMethodArgumentNotValid");
        BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("422", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>("400", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EventDoesNotExistException.class})
    protected ResponseEntity<String> handleDoesNotExist() {
        loggerREH.error("EventDoesNotExistException");
        return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);
    }
}