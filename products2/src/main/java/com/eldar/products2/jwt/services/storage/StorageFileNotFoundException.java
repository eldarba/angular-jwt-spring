package com.eldar.products2.jwt.services.storage;

public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 1L;

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageFileNotFoundException(String message) {
		super(message);
	}

}
