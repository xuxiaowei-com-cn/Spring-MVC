package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.entity.Person;
import cn.com.xuxiaowei.entity.User;
import cn.com.xuxiaowei.entity.UserInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Get 请求
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@RestController
public class GetRestController {

    /**
     * %s：占位符，使用 {@link String#format(String, Object...)} 替换占位符
     */
    private static final String TEMPLATE = "Hello, %s!";

    /**
     * 自增序列号
     */
    private final AtomicLong counter = new AtomicLong();

    /**
     * 获取实体类
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 返回实体类，由于 {@link UserInfo} 使用了 {@link JacksonXmlRootElement} 等注解，默认返回为 XML
     */
    @GetMapping("/getUserInfo1")
    public UserInfo getUserInfo1(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(counter.incrementAndGet());
        userInfo.setUsername("徐晓伟");
        userInfo.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("userInfo：{}", userInfo);
        return userInfo;
    }

    /**
     * 获取实体类
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 返回实体类，{@link User} 转换为 JSON
     */
    @GetMapping(value = "/getUserInfoJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfo getUserInfoJson(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(counter.incrementAndGet());
        userInfo.setUsername("徐晓伟");
        userInfo.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("userInfo：{}", userInfo);
        return userInfo;
    }

    /**
     * 获取实体类
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 返回实体类，{@link User} 转换为 XML
     */
    @GetMapping(value = "/getUserInfoXml", produces = MediaType.APPLICATION_XML_VALUE)
    public UserInfo getUserInfoXml(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(counter.incrementAndGet());
        userInfo.setUsername("徐晓伟");
        userInfo.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("userInfo：{}", userInfo);
        return userInfo;
    }

    /**
     * 获取实体类
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 返回实体类，由于 {@link User} 使用了 {@link XmlRootElement}、{@link XmlElement} 注解，默认返回为 XML
     */
    @GetMapping("/getUser1")
    public User getUser1(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        User user = new User();
        user.setUserId(counter.incrementAndGet());
        user.setUsername("徐晓伟");
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("user：{}", user);
        return user;
    }

    /**
     * 具有参数的 Get 请求
     *
     * @param request
     * @param response
     * @param name     姓名，如果未传入，默认为：World，
     *                 {@link RequestParam#value()}：接收参数的名称
     *                 {@link RequestParam#defaultValue()}：默认值
     * @return
     */
    @GetMapping("/getHi")
    public String getHi(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format(TEMPLATE, name);
    }

    /**
     * 获取实体类（JSON 类型）
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 返回实体类，{@link User} 转换为 JSON
     */
    @GetMapping(value = "/getUserJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserJson(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        User user = new User();
        user.setUserId(counter.incrementAndGet());
        user.setUsername("徐晓伟");
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("user：{}", user);
        return user;
    }

    /**
     * 获取实体类（XML 类型）
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 返回实体类，{@link User} 转换为 XML
     */
    @GetMapping(value = "/getUserXml", produces = MediaType.APPLICATION_XML_VALUE)
    public User getUserXml(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        User user = new User();
        user.setUserId(counter.incrementAndGet());
        user.setUsername("徐晓伟");
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("user：{}", user);
        return user;
    }

    /**
     * 获取实体类（XML、JSON 类型）
     * <p>
     * 默认为 XML
     * <p>
     * Accept：application/json，指定返回为 JSON
     * Accept：application/xml，指定返回为 XML
     *
     * @param request
     * @param response
     * @param username 参数
     * @return 默认返回为 XML（浏览器默认返回为 XML，Postman 默认返回为 JSON）
     */
    @GetMapping(value = "/getUserXmlJson", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User getUserXmlJson(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        User user = new User();
        user.setUserId(counter.incrementAndGet());
        user.setUsername("徐晓伟");
        user.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("user：{}", user);
        return user;
    }

    /**
     * 根据 Accept（application/json、application/xml） 返回数据
     * <p>
     * 由于引入了 jackson-dataformat-xml 依赖，默认返回值不是 JSON，而是 XML
     *
     * @param request
     * @param response
     * @param username
     * @return 默认返回 XML（由于引入了 jackson-dataformat-xml），可根据 Accept 指定返回类型
     */
    @RequestMapping("/getPerson")
    public Person getPerson(HttpServletRequest request, HttpServletResponse response, String username) {
        log.debug("username：{}", username);
        Person person = new Person();
        person.setUserId(counter.incrementAndGet());
        person.setUsername("徐晓伟");
        person.setPassword(UUID.randomUUID().toString().replace("-", ""));
        log.debug("person：{}", person);
        return person;
    }

}
