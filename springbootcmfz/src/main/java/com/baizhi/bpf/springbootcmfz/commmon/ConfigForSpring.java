package com.baizhi.bpf.springbootcmfz.commmon;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class ConfigForSpring {

    @Bean
    public HttpMessageConverters getFastjsonHttpMessageConvertest(){

        FastJsonHttpMessageConverter httpFastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        httpFastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = httpFastConverter;
        return new HttpMessageConverters(converter);
    }

}
