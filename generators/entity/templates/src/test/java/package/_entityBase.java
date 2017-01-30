package com.mycompany.myapp;
import com.mycompany.myapp.web.rest.<%= entityClass %>Resource;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.Before;

public class <%= entityClass %>Base {
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new <%= entityClass %>Resource());
	}

}