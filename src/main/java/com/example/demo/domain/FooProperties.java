package com.example.demo.domain;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "foo")
public class FooProperties {

		private String username;

		private int userAge;

		private String userEmail;

		private Map<String, String> userInterestMap;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public int getUserAge() {
			return userAge;
		}

		public void setUserAge(int userAge) {
			this.userAge = userAge;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public Map<String, String> getUserInterestMap() {
			return userInterestMap;
		}

		public void setUserInterestMap(Map<String, String> userInterestMap) {
			this.userInterestMap = userInterestMap;
		}

		@Override
		public String toString() {
			return "FooProperties [username=" + username + ", userAge=" + userAge + ", userEmail=" + userEmail
					+ ", userInterestMap=" + userInterestMap + "]";
		}
		
		
		
}

