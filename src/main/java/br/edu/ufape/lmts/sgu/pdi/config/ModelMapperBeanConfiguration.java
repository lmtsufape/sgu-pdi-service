package br.edu.ufape.lmts.sgu.pdi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBeanConfiguration {
	@Bean
	public ModelMapper modelMapper() {	
		ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration()
	               .setSkipNullEnabled(true);
	    return modelMapper;
	}	
}