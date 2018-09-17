package cn.zsza.config;

import cn.zsza.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Created By zhangsong
 * 21:39 2018/8/28
 *  WebMvcConfigurerAdapter 该类在Spring5中过时了
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Bean
	RequestInterceptor getRequestInterceptor(){
		return new RequestInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getRequestInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/rabbit/login")
				.excludePathPatterns("/rabbit/myAn");
	}
}
