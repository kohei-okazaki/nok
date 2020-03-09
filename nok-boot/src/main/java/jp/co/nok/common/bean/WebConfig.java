package jp.co.nok.common.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.nok.common.log.RequestTrackingInterceptor;

/**
 * WebのBean定義を設定するクラス
 * 
 * @version 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private RequestTrackingInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);
	}
}
