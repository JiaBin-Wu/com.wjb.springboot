package com.wjb.springboot.service.ex;

/**
 * 更新时，遇到不可预知异常
 * @author wjb
 *
 */
public class UpdateException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921581067455993565L;

	public UpdateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
