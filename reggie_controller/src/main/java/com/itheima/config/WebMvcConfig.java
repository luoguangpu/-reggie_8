package com.itheima.config;//package com.itheima.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//import java.util.List;
//
///*
//    这是一个配置类，用来告诉springboot，静态资源在哪里。
//        1. 继承WebMvcConfigurationSupport 类
//        2. 重写方法addResourceHandlers
//        3. 类上添加注解@Configuration
// */
//@Component
//public class WebMvcConfig extends WebMvcConfigurationSupport {
//
//    /**
//     * 添加资源处理器 ： 主要针对静态资源
//     * @param registry 资源处理器注册对象，使用它可以添加|收录 ： 什么样的请求地址要到什么什么样的地址去拿资源
//     */
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        //添加访问的地址和资源位置的映射关系
//        // 以后只要有前缀是 /backend/... 这样的请求过来，就到类路径（resources下面的资源会搬到类路径去）的/backend文件夹里面找资源
//        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
//        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
//    }
//
//    //重写扩展的消息转换器，在里面给这个参数converters 追加消息转换器
//    //converters springmvc里面的所有转换器集合
//    @Override
//    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        //1. 创建消息转换器11110
//        MappingJackson2HttpMessageConverter mc = new MappingJackson2HttpMessageConverter();
//
//        //2. 设置jackson 转换消息时候，使用我们提供的规则
//        mc.setObjectMapper(new JacksonObjectMapper());
//
//        //3. 把自己的mc转换器添加到转换器集合中 , 顺便把自己的这个转换器放置在结合的第0位，因为
//        // 集合里面已经内置了jackson的消息转换器，必须要让我们的转换器在它的前面，才能生效。
//        converters.add(0, mc);
//
//    }
//}
