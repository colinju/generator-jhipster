package com.mycompany.myapp;

import org.junit.Before;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.mycompany.myapp.web.rest.<%= entityClass %>Resource;

public class <%= entityClass %>Base {
    @Before
    public void setup() {
    RestAssuredMockMvc.standaloneSetup(new <%= entityClass %>Resource());
    }

}