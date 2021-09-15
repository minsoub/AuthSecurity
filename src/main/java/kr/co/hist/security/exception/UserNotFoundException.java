package kr.co.hist.security.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userEmail) {
        super(userEmail + " NotFoundException");
    }
}
