package com.wjb.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wjb.springboot.controller.ex.FileEmptyException;
import com.wjb.springboot.controller.ex.FileIOException;
import com.wjb.springboot.controller.ex.FileSizeException;
import com.wjb.springboot.controller.ex.FileStateException;
import com.wjb.springboot.controller.ex.FileTypeException;
import com.wjb.springboot.entity.User;
import com.wjb.springboot.service.IUserService;
import com.wjb.springboot.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		JsonResult<Void> jr=new JsonResult<Void>();
		userService.reg(user);
		jr.setState(20);
		return jr;
	}
	
	@RequestMapping("login")
	public JsonResult<User> login(String username,String password,HttpSession session){
		JsonResult<User> jr=new JsonResult<User>();
		User user=userService.login(username, password);
		session.setAttribute("uid",user.getUid());
		session.setAttribute("username", username);
		jr.setData(user);
		jr.setState(20);
		return jr;
		
		
	}
	
	@RequestMapping("change_password")
	public JsonResult<Void> changePassword(@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword,HttpSession session){
		
		// 从session中获取uid
		Integer uid=getUidFromSession(session);
		
		// 从session中获取username
		String username=getUsernameFromSession(session);

		userService.changePassword(uid,oldPassword,newPassword,username);
		return new JsonResult<Void>(20);
	}
	
	@GetMapping("get_by_uid")
	public JsonResult<User> getByUid(HttpSession session){
		// 从session中获取uid
		Integer uid=getUidFromSession(session);

		// 调用service获取用户数据
		User user=userService.getByUid(uid);
		User info=new User();
		info.setUsername(user.getUsername());
		info.setPhone(user.getPhone());
		info.setEmail(user.getEmail());
		info.setGender(user.getGender());
		// 返回用户数据
		return new JsonResult<User>(SUCCESS, info);
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> changeInfo(User user,HttpSession session){
		// 从session中获取uid
		Integer uid=getUidFromSession(session);
		
		// 从session中获取username
		String username=getUsernameFromSession(session);

		// 调用service方法更新用户数据
		userService.changeInfo(uid, username, user);
		
		return new JsonResult<>(SUCCESS);
	}
	
	private static final long AVATAR_MAX_SIZE=1*1024*1024;
	private static final List<String> AVATAR_TYPES=new ArrayList<String>();
	
	// 静态初始化器：用于初始化本类的静态成员
	static {
		AVATAR_TYPES.add("image/jpeg");
		AVATAR_TYPES.add("image/png");
	}
	
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
			HttpServletRequest request,HttpSession session
			){
		// 空文件验证
		if(file.isEmpty()) {
			throw new FileEmptyException("文件上传异常！文件不能为空");
		}
		// 文件大小验证
		long fileSize=file.getSize();
		if(fileSize>AVATAR_MAX_SIZE) {
			throw new FileSizeException("文件上传异常！文件大小超过上限:"+(AVATAR_MAX_SIZE/1024)+"kb");
		}
		// 文件类型验证
		if(!AVATAR_TYPES.contains(file.getContentType())) {
			throw new FileTypeException("文件上传异常！文件类型不正确，允许的类型有："+AVATAR_TYPES);
		}
		//生成文件名
		String oFilename=file.getOriginalFilename();
		Integer index=oFilename.lastIndexOf(".");
		String suffix="";
		if(index!=-1) {
			suffix=oFilename.substring(index);
		}
		String filename=UUID.randomUUID().toString()+suffix;
		//生成目标路径
		String filePath=request.getServletContext().getRealPath("upload");
		File parent=new File(filePath);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		File dest=new File(parent,filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileStateException("文件上传异常！"+e.getMessage());
		}catch (IOException e) {
			throw new FileIOException("文件上传异常！"+e.getMessage());
		}
		String avatar="/upload/"+filename;
		Integer uid=getUidFromSession(request.getSession());
		String username=getUsernameFromSession(request.getSession());
		userService.changeAvatar(uid,avatar,username);
		return new JsonResult<String>(SUCCESS,avatar);
	}

	
	
	
}
