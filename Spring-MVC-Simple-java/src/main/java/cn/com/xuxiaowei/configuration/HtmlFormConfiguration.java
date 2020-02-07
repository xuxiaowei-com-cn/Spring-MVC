package cn.com.xuxiaowei.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;

import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义 HTML form 配置
 * <p>
 * JSF POST CSRF 配置
 *
 * @author xuxiaowei
 * @see CsrfTokenRepository
 * @see LazyCsrfTokenRepository
 * @see HttpSessionCsrfTokenRepository
 * @since 0.0.1
 */
@Configuration
public class HtmlFormConfiguration extends HtmlForm {

    /**
     * CSRF
     */
    private HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

    /**
     * 标签 id 样式
     */
    private String id = "%s:%s";

    @Override
    public void encodeEnd(FacesContext context) throws IOException {

        csrfTokenInput(context);

        super.encodeEnd(context);
    }

    /**
     * CSRF Token input
     *
     * @param context
     * @throws IOException
     */
    private void csrfTokenInput(FacesContext context) throws IOException {

        // 加载或创建 CsrfToken
        CsrfToken csrfToken = loadOrGenerateToken(context);

        // CSRF Token name
        String parameterName = csrfToken.getParameterName();

        // CSRF Token value
        String token = csrfToken.getToken();

        // 组合标签 id
        id = String.format(id, getClientId(context), parameterName);

        ResponseWriter csrfInput = context.getResponseWriter();
        csrfInput.startElement("input", null);
        csrfInput.writeAttribute("type", "hidden", null);
        csrfInput.writeAttribute("id", id, null);
        csrfInput.writeAttribute("name", "_csrf", null);
        csrfInput.writeAttribute("value", token, null);
        csrfInput.endElement("input");

    }

    /**
     * 加载或创建 {@link CsrfToken}
     *
     * @param context
     */
    private CsrfToken loadOrGenerateToken(FacesContext context) {

        // 获取 HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        // 加载 HttpServletRequest
        CsrfToken csrfToken = csrfTokenRepository.loadToken(request);

        // 不存在时创建
        if (csrfToken == null) {
            csrfToken = csrfTokenRepository.generateToken(request);
        }

        return csrfToken;
    }

}
