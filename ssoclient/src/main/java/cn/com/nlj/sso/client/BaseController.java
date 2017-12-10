package cn.com.nlj.sso.client;

import java.beans.PropertyEditorSupport;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

/***
 * 类说明：
 * 
 * @author nlj 2017年12月10日 下午1:03:11
 */
@Controller
public class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}
	
	
	class StringEscapeEditor extends PropertyEditorSupport{
		
		private boolean escapeHTML;// 编码HTML
		private boolean escapeJavaScript;// 编码javascript
		
		public StringEscapeEditor() {
			super();
		}
		
		public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
			super();
			this.escapeHTML = escapeHTML;
			this.escapeJavaScript = escapeJavaScript;
		}

		@Override
		public String getAsText() {
			String text = super.getAsText();
			return text == null ? "" : text;
		}

		@Override
		public void setAsText(String text) throws IllegalArgumentException {

			if (text == null) {
				super.setAsText(text);
			} else {
				if (escapeHTML) {
					text = HtmlUtils.htmlEscape(text);
				}
				if (escapeJavaScript) {
					text = JavaScriptUtils.javaScriptEscape(text);
				}
				super.setAsText(text);
			}
		}
	}
}
