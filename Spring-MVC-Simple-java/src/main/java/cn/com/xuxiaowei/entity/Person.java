package cn.com.xuxiaowei.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * XML JSON 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = 2523677215747004097L;

    private Long userId;

    private String username;

    private String password;

}
