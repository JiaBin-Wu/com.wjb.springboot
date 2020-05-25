package com.wjb.springboot.service.ex;

/**
 * 用用户名查密码时，密码不匹配
 * @author wjb
 *
 */
public class PasswordNotMatchException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7475864738802350846L;

	public PasswordNotMatchException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
