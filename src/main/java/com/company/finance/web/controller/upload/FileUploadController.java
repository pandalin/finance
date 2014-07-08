package com.company.finance.web.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.finance.web.controller.BaseController;

/**
 * <p>
 * 文件上传
 *
 * @author linxiaomin@sina.cn
 * @date 2014年7月8日 下午4:07:04
 * @version v1.0
 */
@Controller
@RequestMapping(value = "/upload")
public class FileUploadController extends BaseController {
	
	@RequestMapping(value="/toupload",method=RequestMethod.GET)
	public String toupload() {
		
		return "upload/upload";
	}

	@RequestMapping(value = "/ajaxUpload",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

//		String path = request.getServletContext().getRealPath("upload");
		
		String path = request.getServletContext().getContextPath()+"/"+"upload";

		String fileName = file.getOriginalFilename();

		File newFile = new File(path, fileName);
		if (!newFile.exists()) {
			newFile.mkdirs();
		}

		try {
			file.transferTo(newFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return ajaxSave(true, "");
	}
}
