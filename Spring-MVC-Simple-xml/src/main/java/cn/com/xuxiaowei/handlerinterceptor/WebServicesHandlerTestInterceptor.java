package cn.com.xuxiaowei.handlerinterceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPException;

/**
 * WebService Handler 测试 拦截器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class WebServicesHandlerTestInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public static final String AUTHORIZATION = "Authorization";

    public static final String USERNAME = "xxw";
    public static final String PASSWORD = "123";

    public static final String SEPARATE = ":";

    public static final String BASIC_AUTH = HttpServletRequest.BASIC_AUTH + " ";

    public WebServicesHandlerTestInterceptor() {
        super(Phase.PRE_INVOKE);
    }

    @Override
    public void handleMessage(SoapMessage message) throws Fault {

        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);

        String authorization = request.getHeader(AUTHORIZATION);

        if (authorization == null) {
            throw new Fault(new SOAPException("授权信息为空！"));
        } else if (!authorization.startsWith(BASIC_AUTH)) {
            throw new Fault(new SOAPException("Authorization 非 baisc 验证"));
        } else {

            // <code>String plaintext = new String(Base64.decodeBase64(authorization.substring(BASIC_AUTH.length())));</code>

            String plaintext = authorization.substring(BASIC_AUTH.length());

            if (plaintext.length() == 0 || !plaintext.contains(SEPARATE)) {
                throw new Fault(new SOAPException("Authorization 非 baisc 验证"));
            }

            String[] usernameAndPass = plaintext.split(SEPARATE);
            String username = usernameAndPass[0];
            String password = usernameAndPass[1];
            if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
                SOAPException exception = new SOAPException("用户名或密码不正确！");
                throw new Fault(exception);
            }

        }

    }

}
