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
package cn.com.xuxiaowei.service.impl;

import cn.com.xuxiaowei.entity.MybatisPlusTest;
import cn.com.xuxiaowei.mapper.MybatisPlusTestMapper;
import cn.com.xuxiaowei.service.IMybatisPlusTestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * MyBatis Plus 测试表 服务实现类
 * </p>
 *
 * @author 徐晓伟
 * @since 2020-02-18
 */
@Service
public class MybatisPlusTestServiceImpl extends ServiceImpl<MybatisPlusTestMapper, MybatisPlusTest> implements IMybatisPlusTestService {
	
}
