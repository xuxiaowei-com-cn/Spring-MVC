package cn.com.xuxiaowei.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * MyBatis Plus 测试表
 * </p>
 *
 * @author 徐晓伟
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mybatis_plus_test")
public class MybatisPlusTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("test_id")
    private Long testId;

    @TableField("test1")
    private String test1;

    @TableField("test2")
    private String test2;


    public static final String TEST_ID = "test_id";

    public static final String TEST1 = "test1";

    public static final String TEST2 = "test2";

}
