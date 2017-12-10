package cn.com.nlj.sso.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 类说明：
 * 
 * @author nlj 2017年12月10日 下午1:03:11
 */
@Controller
public class LoadPageController extends BaseController{

	@RequestMapping("/load/{folder}/{pageName}")
	public String logdPage(@PathVariable String folder, @PathVariable String pageName) {
		return "/" + folder + "/" + pageName;
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
}
