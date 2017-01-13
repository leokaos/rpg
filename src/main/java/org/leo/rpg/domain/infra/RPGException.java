package org.leo.rpg.domain.infra;

public class RPGException extends Exception {

	private static final long serialVersionUID = 1581975500617126020L;

	public RPGException() {
		super();
	}

	public RPGException(String message, Throwable cause) {
		super(message,cause);
	}

	public RPGException(String message) {
		super(message);
	}

	public RPGException(Throwable cause) {
		super(cause);
	}

}
