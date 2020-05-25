package com.wjb.springboot.service.ex;

/**
 * 插入数据时遇到不可知异常
 * @author wjb
 *
 */
public class InsertException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5155767646890009538L;

	public InsertException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsertException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
