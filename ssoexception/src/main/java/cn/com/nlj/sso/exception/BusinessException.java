package cn.com.nlj.sso.exception;
/**
 * @classDesc 类说明：
 * @author NLJ 2017年12月18日 下午7:31:40
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5708291993514483656L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
	}
	
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
