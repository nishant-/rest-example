package rest.example.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rest.example.model.response.ErrorMessage;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest webRequest) {

        String errDesc = ex.getLocalizedMessage() == null? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = ErrorMessage.builder().
                timeStamp(new Date()).
                message(errDesc).
                build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest webRequest) {

        String errDesc = ex.getLocalizedMessage() == null? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = ErrorMessage.builder().
                timeStamp(new Date()).
                message(errDesc).
                build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest webRequest) {

        String errDesc = ex.getLocalizedMessage() == null? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = ErrorMessage.builder().
                timeStamp(new Date()).
                message(errDesc).
                build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //to handle multiple exceptions, effectively one method to handle multiple exceptions
   /* @ExceptionHandler(value = {UserServiceException.class, NullPointerException.class})
    public ResponseEntity<Object> handleMultipleExceptions(Exception ex, WebRequest webRequest) {

        String errDesc = ex.getLocalizedMessage() == null? ex.toString() : ex.getLocalizedMessage();

        ErrorMessage errorMessage = ErrorMessage.builder().
                timeStamp(new Date()).
                message(errDesc).
                build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}
