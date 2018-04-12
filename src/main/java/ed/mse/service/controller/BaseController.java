package ed.mse.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
	
	@Autowired 
	BaseService service;

//	@RequestMapping(value = "/path")
//	@ResponseBody
//	public ResponsePath path(@RequestParam("start") String start, @RequestParam("end") String end) { 
//
//		return service.getPath(start, end);
//	}
	
	@RequestMapping(value = "/path")
	@ResponseBody
	public String path() { 
		return service.getPath();
	}

	@RequestMapping(value = "/helloworld")
	public String helloworld() { 
		return "Hello World!";
	}
	
	
}
