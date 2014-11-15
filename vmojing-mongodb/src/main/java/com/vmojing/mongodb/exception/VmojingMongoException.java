package com.vmojing.mongodb.exception;


public class VmojingMongoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public VmojingMongoException(final String message) {
		super(message);
	}

	public VmojingMongoException(final String message, final Throwable t) {
		super(message, t);
	}

	public VmojingMongoException(final Throwable t) {
		super(t);
	}
}
