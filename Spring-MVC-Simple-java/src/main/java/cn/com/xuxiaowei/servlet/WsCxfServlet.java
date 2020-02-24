package cn.com.xuxiaowei.servlet;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.transport.servlet.CXFServlet;

import javax.servlet.annotation.WebServlet;

/**
 * CXF 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@WebServlet(name = "wsCxfServlet", urlPatterns = "/ws/*")
public class WsCxfServlet extends CXFServlet {

}
