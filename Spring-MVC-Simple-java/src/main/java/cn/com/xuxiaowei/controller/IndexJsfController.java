package cn.com.xuxiaowei.controller;

import cn.com.xuxiaowei.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * JSF Controller
 *
 * <code>@ManagedBean</code>
 * <code>@SessionScoped</code>
 *
 * @author xuxiaowei
 * @see Serializable 用于 Session 共享的序列化
 * @since 0.0.1
 */
@Slf4j
@Component
@Scope
public class IndexJsfController implements Serializable {

    /**
     * 用于 Session 共享的序列化
     */
    private static final long serialVersionUID = -8377204181936446253L;

    private ITestService testService;

    @Autowired
    public void setTestService(ITestService testService) {
        this.testService = testService;
    }

    public String getIndex() {

        log.debug(testService.hi());

        return this.toString();
    }

    public Date getNow() {
        return new Date();
    }

}
