package tech.foxlo.bookingservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.foxlo.bookingservice.exception.StationNotFoundException;

@ControllerAdvice
public class StationExceptionController {

    @ExceptionHandler(StationNotFoundException.class)
    public ResponseEntity<ProblemDetail> exceptionStationNotFound(StationNotFoundException se){
        var pb = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, se.getMessage());
        return ResponseEntity.ok(pb);
    }
}
