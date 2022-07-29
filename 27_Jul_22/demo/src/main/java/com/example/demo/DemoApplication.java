package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	private static class A {
//		@Autowired
//		B b;
//		public A () {
////			this.b = new B();
//		}
//	}
//
//	@Component
//	private static class B {
//
//	}
}
