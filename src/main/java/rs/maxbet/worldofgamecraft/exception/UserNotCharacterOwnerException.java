package rs.maxbet.worldofgamecraft.exception;

public class UserNotCharacterOwnerException extends RuntimeException {
    public UserNotCharacterOwnerException(String message) {
        super(message);
    }
}