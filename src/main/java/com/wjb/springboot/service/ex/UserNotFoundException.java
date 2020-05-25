package com.wjb.springboot.service.ex;

/**
 * 根据用户名查找用户，未找到相关用户
 * @author wjb
 *
 */
public class UserNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5596947037491236466L;

	public UserNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
