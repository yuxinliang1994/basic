package com.example.demo;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.ServletRequestHandledEvent;


@SpringBootApplication
public class BasicApplication {

	private static final Logger log = LoggerFactory.getLogger(BasicApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			log.debug("Using log4j2 ...... ?");
			System.out.println();
			System.out.println("CommandLine Runner:");
			for (String arg : args) {
				System.out.println(arg);
			}
		};
	}
	
	@Bean
	public ApplicationRunner appRunner() {
		return args -> {
			System.out.println();
			System.out.println("Application Runner:");
			for (String opt : args.getOptionNames()) {
				System.out.print(opt);
				System.out.print("->");
				System.out.println(args.getOptionValues(opt).stream().collect(Collectors.joining(",", "[", "]")));
				// String.join(",", args.getOptionValues(opt));
			}
		};
	}
	/*需要加载spring-boot-starter-actuator*/
	@Autowired
	private CounterService counterService;
	
	@Bean
	public ApplicationListener<ApplicationEvent> xyzListener() {
		final String HELLO_URL = "/xyz";

		return (ApplicationEvent event) -> {
			if (event instanceof ServletRequestHandledEvent) {
				ServletRequestHandledEvent e = (ServletRequestHandledEvent) event;
				if (e.getRequestUrl().equals(HELLO_URL))
					counterService.increment("xyz.hits");
			}
		};
	}
	//是否可以访问互联网 add by xuconghui 20180530
	@Bean
	public HealthIndicator myHealth() {
		return () -> {
			RestTemplate restTemplate = new RestTemplate();
			try {
				String string = restTemplate.getForObject("https://www.taobao.com", String.class);
				return Health.up().build();
			} catch (Exception e) {
				return Health.down().withDetail("Error Code", 404).build();
			}
		};
	}
	/*@Bean
	public HealthIndicator myHealth() {
		return () -> {
			int math = (int)(Math.random()*(10));
			if (math>=5) {
				System.out.println("math"+math);
				return Health.up().build();
			}else {
				System.out.println("math"+math);
				return Health.down().withDetail("不能联网", 404).build();
			}
		};
	}*/
	/*@Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propsConfig 
          = new PropertySourcesPlaceholderConfigurer();
        propsConfig.setLocation(new ClassPathResource("git.properties"));
        propsConfig.setIgnoreResourceNotFound(true);
        propsConfig.setIgnoreUnresolvablePlaceholders(true);
        return propsConfig;
    }*/
	
}
