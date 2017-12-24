package cn.com.nlj.sso.client;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.nlj.sso.dto.UserDto;

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
	public String index(Model model) {
		Subject subject = SecurityUtils.getSubject();
		UserDto userDto = (UserDto) subject.getSession().getAttribute("user");
		model.addAttribute("userName", userDto.getUserName());
		return "index";
	}
}
