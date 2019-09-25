package com.coordination.style;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coordination.dto.StyleVO;
import com.coordination.service.StyleService;

@Controller
public class UpdateStyle {
	
	@Autowired
	private StyleService service;
	
	@RequestMapping(value = "updateStyle", method = RequestMethod.POST)
	public String update(StyleVO vo, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			vo.setNum(1);
			service.updateStyle(vo);
			
			out.println("<script>"
					+ "alert('이미지의 정보가 수정되었습니다.');"
        			+ "</script>");
            out.flush();
			
		}catch(Exception e) {
			e.printStackTrace();
			
			out.println("<script>"
					+ "alert('Error!!!');"
					+ "history.back();"
        			+ "</script>");
            out.flush();
		}
		
		return null;
	}
}
