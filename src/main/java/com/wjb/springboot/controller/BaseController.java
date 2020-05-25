package com.wjb.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjb.springboot.controller.ex.FileEmptyException;
import com.wjb.springboot.controller.ex.FileIOException;
import com.wjb.springboot.controller.ex.FileSizeException;
import com.wjb.springboot.controller.ex.FileStateException;
import com.wjb.springboot.controller.ex.FileTypeException;
import com.wjb.springboot.service.ex.AccessDeniedException;
import com.wjb.springboot.service.ex.AddressCountLimitException;
import com.wjb.springboot.service.ex.AddressNotFoundException;
import com.wjb.springboot.service.ex.CartNotFoundException;
import com.wjb.springboot.service.ex.DeleteException;
import com.wjb.springboot.service.ex.InsertException;
import com.wjb.springboot.service.ex.PasswordNotMatchException;
import com.wjb.springboot.service.ex.ProductNotFoundException;
import com.wjb.springboot.service.ex.ServiceException;
import com.wjb.springboot.service.ex.UpdateException;
import com.wjb.springboot.service.ex.UserNotFoundException;
import com.wjb.springboot.service.ex.UsernameDuplicationException;
import com.wjb.springboot.util.JsonResult;

public abstract class BaseController {
	
	protected static final Integer SUCCESS=20;
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult<Void> handleException(Throwable e) {
		JsonResult<Void> jr=new JsonResult<Void>();
		if(e instanceof UsernameDuplicationException) {
			jr.setState(30);
			jr.setMessage(e.getMessage());
		}else if(e instanceof UserNotFoundException) {
			jr.setState(31);
			jr.setMessage(e.getMessage());
		}else if(e instanceof PasswordNotMatchException) {
			jr.setState(32);
			jr.setMessage(e.getMessage());
		}else if(e instanceof AddressCountLimitException) {
			jr.setState(33);
			jr.setMessage(e.getMessage());
		}else if(e instanceof AddressNotFoundException) {
			jr.setState(34);
			jr.setMessage(e.getMessage());
		}else if(e instanceof AccessDeniedException) {
			jr.setState(35);
			jr.setMessage(e.getMessage());
		}else if(e instanceof ProductNotFoundException) {
			jr.setState(36);
			jr.setMessage(e.getMessage());
		}else if(e instanceof CartNotFoundException) {
			jr.setState(37);
			jr.setMessage(e.getMessage());
		}else if(e instanceof InsertException) {
			jr.setState(40);
			jr.setMessage(e.getMessage());
		}else if(e instanceof UpdateException) {
			jr.setState(41);
			jr.setMessage(e.getMessage());
		}else if(e instanceof DeleteException) {
			jr.setState(42);
			jr.setMessage(e.getMessage());
		}else if(e instanceof FileEmptyException) {
			jr.setState(50);
			jr.setMessage(e.getMessage());
		}else if(e instanceof FileSizeException) {
			jr.setState(51);
			jr.setMessage(e.getMessage());
		}else if(e instanceof FileTypeException) {
			jr.setState(52);
			jr.setMessage(e.getMessage());
		}else if(e instanceof FileStateException) {
			jr.setState(53);
			jr.setMessage(e.getMessage());
		}else if(e instanceof FileIOException) {
			jr.setState(54);
			jr.setMessage(e.getMessage());
		}
		return jr;
		
	}
	
	public String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}

	public Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
}
