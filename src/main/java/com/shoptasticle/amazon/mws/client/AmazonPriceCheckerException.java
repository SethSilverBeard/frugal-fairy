package com.shoptasticle.amazon.mws.client;

public class AmazonPriceCheckerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public AmazonPriceCheckerException() {
	}

	public AmazonPriceCheckerException(String message) {
		super(message);
	}

	public AmazonPriceCheckerException(Throwable cause) {
		super(cause);
	}

	public AmazonPriceCheckerException(String message, Throwable cause) {
		super(message, cause);
	}

	public AmazonPriceCheckerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
