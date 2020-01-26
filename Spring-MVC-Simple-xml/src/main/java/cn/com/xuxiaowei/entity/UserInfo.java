package cn.com.xuxiaowei.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户测试 XML Bean 转化
 * <p>
 * {@link JacksonXmlRootElement#namespace()}：无效
 * {@link JacksonXmlRootElement#localName()}：不可设置
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
@JacksonXmlRootElement
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4191739916188118828L;

    /**
     * 放在父节点上
     */
    @JacksonXmlProperty(isAttribute = true)
    private Long userId;

    /**
     * 使用 CDATA 返回数据
     */
    @JacksonXmlCData
    private String username;

    /**
     * 自定义转化为 XML 时的属性名
     */
    @JacksonXmlProperty(localName = "pw")
    private String password;

}
