package com.wjb.springboot.service.ex;

/**
 * 查询用户名，用户名已存在
 * @author wjb
 *
 */
public class UsernameDuplicationException extends ServiceException {

	
	private static final long serialVersionUID = -5871038098868518627L;

	public UsernameDuplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
