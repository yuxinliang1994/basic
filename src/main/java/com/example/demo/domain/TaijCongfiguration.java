package com.example.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FooProperties.class)
public class TaijCongfiguration {
	@Autowired
	private FooProperties fooProperties;
	//重写toString
	public String toString() {
		return fooProperties.toString();
	}
}
