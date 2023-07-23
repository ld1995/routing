package by.ld1995.routing.controller;

import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ControllerAdvice {

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<String> handleConstraintViolationException(
      final ConstraintViolationException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleException(final Exception exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }

}
