/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
