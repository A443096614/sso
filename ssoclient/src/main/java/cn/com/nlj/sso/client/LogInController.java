package cn.com.nlj.sso.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import cn.com.nlj.sso.dto.R;
import cn.com.nlj.sso.exception.BusinessException;

/***
 * 类说明：
 * 
 * @author nlj 2017年12月10日 下午10:21:25
 */
@Controller
public class LogInController {

	@Autowired
	private DefaultKaptcha defaultKaptcha;
	
	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			request.getSession().setAttribute("vrifyCode", createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		out.write(captchaChallengeAsJpeg);
		out.flush();
		out.close();
	}

	@ResponseBody
	@PostMapping("/sys/login")
	public R login(HttpServletRequest request, String userNo, String passWord, String captcha) {
		String vrifyCode = (String) request.getSession().getAttribute("vrifyCode");
		if (!vrifyCode.equals(captcha)) {
			return R.error("输入的验证码错误，请重新输入!");
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userNo, passWord);
		try {
			// 执行登录.
			subject.login(token);
		// 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常.
		} catch (UnknownAccountException uae) {
			return R.error(uae.getMessage());
		// 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。
		} catch (IncorrectCredentialsException ice) {
			return R.error("输入的密码不正确，请重新输入！");
		// 用户被锁定的异常 LockedAccountException
		} catch (LockedAccountException lae) {
			return R.error(lae.getMessage());
		// 所有认证时异常的父类.
		} catch (AuthenticationException ae) {
			Throwable cause = ae.getCause();
			if (cause instanceof BusinessException) {
				return R.error(cause.getMessage());
			}
			return R.error("输入的用户名或密码不正确，请重新输入！");
		}
		return R.ok();
	}
	
	@GetMapping("/sys/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login.html";
	}
}
