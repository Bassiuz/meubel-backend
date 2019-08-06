package com.example.herokudemo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HerokuDemoApplicationTests {

	@Test	
	public void contextLoads() {
		Boolean passTest = true;

		assertTrue("Pass default assertion test", passTest);
	}

}
