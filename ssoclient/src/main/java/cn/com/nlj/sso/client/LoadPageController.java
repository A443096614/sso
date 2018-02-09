package cn.com.nlj.sso.client;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.nlj.sso.dto.UserDto;
import cn.com.nlj.sso.shiro.utils.WebUtil;

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
	
	@RequestMapping("/main")
	public String main(Model model) {
		UserDto userDto = WebUtil.userDto();
		model.addAttribute("userName", userDto.getUserName());
		return "main";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.getSession().getAttribute("user") != null) {
			return "main";
		}
		return "login";
	}
}
