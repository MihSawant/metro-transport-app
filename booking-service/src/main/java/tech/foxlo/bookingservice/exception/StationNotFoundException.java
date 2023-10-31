package tech.foxlo.bookingservice.exception;


public class StationNotFoundException extends RuntimeException{

    private final String message;

    public StationNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
