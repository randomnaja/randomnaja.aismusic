package randomnaja.aismusic.exception;

public class GeneralAisMusicException extends RuntimeException {

    public GeneralAisMusicException(String message) {
        super(message);
    }

    public GeneralAisMusicException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralAisMusicException(Throwable cause) {
        super(cause);
    }
}
