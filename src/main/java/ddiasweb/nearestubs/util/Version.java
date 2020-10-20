package com.nearestubs.nearestubs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Version {

	@Value("${project.version}")
	private String version;
	
	@Bean
	public String getVersion() {
		return version;
	}
	
}
