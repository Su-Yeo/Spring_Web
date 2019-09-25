package com.coordination.member;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.coordination.dto.MemberVO;
import com.coordination.service.MemberService;

@Controller
public class UpdateMember {

	@Inject
	private MemberService service;
	
	@RequestMapping(value = "updateMember", method = RequestMethod.POST)
	public ModelAndView insert(MemberVO vo, HttpServletResponse response)throws Exception {
	
		ModelAndView mav = new ModelAndView();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			service.updateMember(vo);
			mav.setViewName("");
			
			out.println("<script>"
					+ "alert('회원정보가 정상적으로 변경되었습니다.');"
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
		return mav;
	}
}
