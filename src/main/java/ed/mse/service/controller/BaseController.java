package ed.mse.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ed.mse.service.response.ResponsePath;
import net.minidev.json.JSONObject;

@RestController
public class BaseController {
	
	@Autowired 
	BaseService service;

	@RequestMapping(value = "/path")
	@ResponseBody
	public ResponsePath path(@RequestParam("start") String start, @RequestParam("end") String end) { 

		return service.getPath(start, end);
	}
	
	@RequestMapping(value = "/helloworld")
	public String path() { 
		return service.helloworld();
	}
	
	
}
