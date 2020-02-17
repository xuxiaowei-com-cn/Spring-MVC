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
