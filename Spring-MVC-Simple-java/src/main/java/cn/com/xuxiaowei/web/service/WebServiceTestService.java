package cn.com.xuxiaowei.web.service;

import cn.com.xuxiaowei.entity.Person;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * 测试 WebService 服务类
 * <p>
 * 可使用注解：{@link SOAPBinding#style()} 设置为 {@link Style#RPC}
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@WebService
public interface WebServiceTestService {

    Person getPerson(@WebParam(name = "username") String username);

}
