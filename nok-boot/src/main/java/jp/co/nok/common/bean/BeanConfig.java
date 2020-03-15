package jp.co.nok.common.bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Bean定義を設定するクラス
 *
 * @version 1.0.0
 */
@Configuration
public class BeanConfig implements WebMvcConfigurer {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		org.modelmapper.config.Configuration conf = modelMapper.getConfiguration();
		conf.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
