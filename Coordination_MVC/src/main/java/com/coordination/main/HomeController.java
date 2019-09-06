package com.coordination.main;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coordination.dao.StyleDAO;
import com.coordination.dto.StyleVO;
import com.coordination.weather.ApiExplorerWeather;
import com.coordination.weather.CoordFetcher;
import com.google.gson.JsonArray;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private StyleDAO styleDAO;
	
	private String top="경기도";
	private String mdl="부천시소사구";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute StyleVO vo, Locale locale, Model model) throws Exception {
		logger.info("Welcome Man & Coordination!!");
		
		return "coordination/index";
	}
	
	// Ajax 지역선택 처리 매핑
	@ResponseBody // view가 아닌 data리턴
    @RequestMapping(value="/weather/selTop", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String selTop(@RequestParam("selTop")String selTop) throws Exception {
        logger.info("selTop : "+selTop);
        CoordFetcher coord = new CoordFetcher();
        JSONArray mapMdl = coord.getMapMdl(selTop);
        //System.out.println(mapMdl.toJSONString());
        top=selTop;
        return mapMdl.toJSONString();
    }
    
    @ResponseBody // view가 아닌 data리턴
    @RequestMapping(value="/weather/selMdl", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String selMdl(@RequestParam("selMdl")String selMdl) throws Exception {
        logger.info("selMdl : "+selMdl);
        CoordFetcher coord = new CoordFetcher();
        JSONArray mapleaf = coord.getMapLeaf(top, selMdl);
        //System.out.println(mapleaf.toJSONString());
        mdl=selMdl;
        return mapleaf.toJSONString();
    }
	
    @ResponseBody // view가 아닌 data리턴
    @RequestMapping(value="/weather/selLeaf", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
    public String selLeaf(@RequestParam("selLeaf")String selLeaf) throws Exception {
        logger.info("selLeaf : "+selLeaf);
        CoordFetcher coord = new CoordFetcher();
        ApiExplorerWeather api = new ApiExplorerWeather(coord.fetchCoord(top,mdl,selLeaf));
    	JsonArray js = api.getJArray();
        //System.out.println(mapleaf.toJSONString());
        return js.toString();
    }
}
