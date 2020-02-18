package cn.com.xuxiaowei.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

	@TableId(value="test_id", type= IdType.AUTO)
	private Long testId;
	private String test1;
	private String test2;

	public static final String TEST_ID = "test_id";

	public static final String TEST1 = "test1";

	public static final String TEST2 = "test2";

}
