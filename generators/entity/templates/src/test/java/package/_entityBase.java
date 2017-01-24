package com.mycompany.myapp;
import com.mycompany.myapp.web.rest.<%=this.entityNameCapitalized%>Resource;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.Before;

public class <%=this.entityNameCapitalized%>Base {
	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new <%=this.entityNameCapitalized%>Resource());
	}

}