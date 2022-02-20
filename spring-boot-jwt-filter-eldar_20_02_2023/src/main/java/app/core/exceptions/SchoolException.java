package app.core.exceptions;

public class SchoolException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SchoolException() {
		super();
	}

	public SchoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SchoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public SchoolException(String message) {
		super(message);
	}

	public SchoolException(Throwable cause) {
		super(cause);
	}

}
