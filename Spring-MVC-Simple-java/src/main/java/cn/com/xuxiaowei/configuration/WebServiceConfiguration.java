package cn.com.xuxiaowei.configuration;

import cn.com.xuxiaowei.handlerinterceptor.WebServicesHandlerTestInterceptor;
import cn.com.xuxiaowei.web.service.WebServiceTestService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * WebService 配置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Configuration
public class WebServiceConfiguration {

    /**
     * 注入 测试 WebService 服务类
     */
    private WebServiceTestService webServicesTestService;

    @Autowired
    public void setWebServicesTestService(WebServiceTestService webServicesTestService) {
        this.webServicesTestService = webServicesTestService;
    }

    /**
     * CXF
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * 注册服务：用于测试的 WebService
     */
    @Bean
    public Endpoint webServicesTestServiceEndpoint() {
        EndpointImpl webServiceTestServiceImpl = new EndpointImpl(springBus(), webServicesTestService);
        webServiceTestServiceImpl.publish("/webServiceTestService");

        // 进入 Handler 测试 拦截器
        webServiceTestServiceImpl.getInInterceptors().add(webServicesHandlerTestInterceptor());

        return webServiceTestServiceImpl;
    }

    /**
     * 将 WebService Handler 测试 拦截器 注册为 {@link Bean}
     */
    @Bean
    public WebServicesHandlerTestInterceptor webServicesHandlerTestInterceptor() {
        return new WebServicesHandlerTestInterceptor();
    }

}
