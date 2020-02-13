package cn.com.xuxiaowei.configuration;

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

    private WebServiceTestService webServiceTestService;

    @Autowired
    public void setWebServiceTestService(WebServiceTestService webServiceTestService) {
        this.webServiceTestService = webServiceTestService;
    }

    /**
     * CXF
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * 注册服务：用于测试的 用户 服务接口
     */
    @Bean
    public Endpoint testUser1ServiceEndpoint() {
        EndpointImpl testUser1ServiceEndpointImpl = new EndpointImpl(springBus(), webServiceTestService);
        testUser1ServiceEndpointImpl.publish("/webServiceTestService");
        return testUser1ServiceEndpointImpl;
    }

}
