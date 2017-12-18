package cn.com.nlj.sso.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.commons.lang.ObjectUtils;

/**
* @author nlj 2017年9月20日 下午3:42:17
*
* 类说明：
*/
public class StringUtil {

	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		String str = (obj == null) ? null : obj.toString();
		boolean isNotEmpty = false;
		if (str != null && !str.trim().equals("") && !str.trim().equalsIgnoreCase("NULL")) {
			isNotEmpty = true;
		}
		return isNotEmpty;
	}

	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return !isNotEmpty(obj);
	}
	
	/***
	 * 为null时返回空字符串
	 * @param obj
	 * @return
	 */
	public static String nullToEmptyStr(Object obj) {
		return emptyWithReturn(obj, "");
	}
	
	/**
	 * 如果字符串不为空，返回他本身，否则返回defaultString
	 * @param str
	 * @param defaultString 默认字符串
	 * @return
	 */
	public static String emptyWithReturn(Object obj, String defaultString) {
		return StringUtil.isEmpty(obj) ? defaultString : obj.toString().trim();
	}
	
	/**
	 * 字符串填充方法
	 * @param source 原始字符串
	 * @param fillStr 填充字符串
	 * @param length 填充后的长度
	 * @param fillType 填充类型 : 0：向左填充, 1:向右填充
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fillString(String source, String fillStr, int length, int fillType) {
		try {
			byte[] data = source.getBytes("UTF-8");
			int nn = length - data.length;
			if (data.length < length) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] tbs = fillStr.getBytes("UTF-8");
				for (int i = 0; i < nn; i += tbs.length)
					bos.write(tbs);
				String str = new String(bos.toByteArray(), 0, nn);
				source = fillType == 0 ? (str + source) : (source + str);
			} else if (data.length > length) {
				throw new RuntimeException("字符串[" + source + "]的长度大于了" + length);
			}
			return source;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将数组转成SQL认识的字符串 123,432,2312 id in('123','432','2312')
	 * @param ids
	 * @return
	 */
	public static String fromArrayToStr(String[] ids) {
		StringBuilder str = new StringBuilder(100);
		for (int i = 0; i < ids.length; i++) {
			str.append("'" + ids[i] + "',");
		}
		if (ids.length > 0) {
			str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}
	
	/**
	 * 将List<String>转换成以separator分隔的字符串 '1','2','3'	
	 * @param iterable
	 * @param separator
	 * @return
	 */
	public static String join(final Iterable<?> iterable, final String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }
	
	public static String join(final Iterator<?> iterator, final String separator) {

        if (iterator == null) {
            return null;
        }
        
        if (!iterator.hasNext()) {
            return "";
        }
        
        // two or more elements
        final StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        
        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            final String result = ObjectUtils.toString(first);
            buf.append("'").append(result).append("'");
            return buf.toString();
        }

       
        if (first != null) {
            buf.append("'").append(first).append("'");
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append("'").append(obj).append("'");
            }
        }
        return buf.toString();
    }
}
