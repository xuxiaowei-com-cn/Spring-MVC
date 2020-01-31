package cn.com.xuxiaowei.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 自定义 {@link ObjectMapper} 提供了从基本 POJO（普通旧Java对象）或从通用 JSON 树模型（{@link JsonNode}）读取和写入 JSON 的功能，以及执行转换的相关功能。
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class CustomObjectMapper extends ObjectMapper {

    /**
     * 自定义序列化
     */
    public static CustomObjectMapper defaultObjectMapper() {

        CustomObjectMapper objectMapper = new CustomObjectMapper();

        // 枚举，定义影响Java对象序列化方式的简单开/关功能。
        // 默认情况下启用功能，因此默认情况下日期/时间序列化为时间戳。
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 如果启用，上下文<code> TimeZone </ code>将基本上覆盖任何其他TimeZone信息;如果禁用，则仅在值本身不包含任何TimeZone信息时使用。
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 序列化时带全限定名
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        return objectMapper;
    }

}
