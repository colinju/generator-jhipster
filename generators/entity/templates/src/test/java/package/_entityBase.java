package <%=packageName%>;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.mycompany.myapp.domain.<%= entityClass %>;
import com.mycompany.myapp.web.rest.<%= entityClass %>Resource;

/**
 * <%= entityClass %>Base is the base class for spring cloud contract.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestCustomGenApp.class)
public class <%= entityClass %>Base{
	
@Before
	public void setup() throws URISyntaxException {
		// mock resource
		<%= entityClass %>Resource mocked<%= entityClass %> = mock(<%= entityClass %>Resource.class);
		
		// 2 objects for return/ list
		<%= entityClass %> obj1 = new <%= entityClass %>(),obj2 = new <%= entityClass %>();
		obj1.setId(1L);
		obj2.setId(2L);
		<%_ 
		for (idx in fields) {
		var fieldType = fields[idx].fieldType;
		var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
		var isEnum = fields[idx].fieldIsEnum;
		var fieldNameCapitalized =  fields[idx].fieldNameCapitalized;
		if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
		obj1.set<%=fieldNameCapitalized %>("test");
		obj2.set<%=fieldNameCapitalized %>("test2");
		<%_ } else if (fieldType == 'Integer') { _%>
		obj1.set<%=fieldNameCapitalized %>(1);
		obj2.set<%=fieldNameCapitalized %>(2);
		<%_ } else if (fieldType == 'Long') { _%>
		obj1.set<%=fieldNameCapitalized %>(1000L);
		obj2.set<%=fieldNameCapitalized %>(2000L);
		<%_ } else if (fieldType == 'Float') { _%>
		obj1.set<%=fieldNameCapitalized %>(10.0f);
		obj2.set<%=fieldNameCapitalized %>(20.0f);
		<%_ } else if (fieldType == 'Double') { _%>
		obj1.set<%=fieldNameCapitalized %>(10.10);
		obj2.set<%=fieldNameCapitalized %>(20.20);
		<%_ } else if (fieldType == 'BigDecimal') { _%>
		obj1.set<%=fieldNameCapitalized %>(0.1000000000000000);
		obj2.set<%=fieldNameCapitalized %>(0.2000000000000000);
		<%_ } else if (fieldType == 'UUID') { _%>
		obj1.set<%=fieldNameCapitalized %>(100000);
		obj2.set<%=fieldNameCapitalized %>(200000);
		<%_ } else if (fieldType == 'LocalDate') { _%>
		obj1.set<%=fieldNameCapitalized %>();
		obj2.set<%=fieldNameCapitalized %>();
		<%_ } else if (fieldType == 'ZonedDateTime') { _%>
		obj1.set<%=fieldNameCapitalized %>();
		obj2.set<%=fieldNameCapitalized %>();
		<%_ } else if (fieldType == 'Boolean') { _%>
		obj1.set<%=fieldNameCapitalized %>(true);
		obj2.set<%=fieldNameCapitalized %>(false);
		<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
		obj1.set<%=fieldNameCapitalized %>(100100100100);
		obj2.set<%=fieldNameCapitalized %>(011011011011);
		<%_ }} _%>		
		
		// entity list for get all
		List<<%= entityClass %>> <%= entityClass.toLowerCase() %>s = new ArrayList<<%= entityClass %>>();
		<%= entityClass.toLowerCase() %>s.add(obj1);
		<%= entityClass.toLowerCase() %>s.add(obj2);
		
		// get
		when(mocked<%= entityClass %>.get<%= entityClass %>(1L)).thenReturn(Optional.ofNullable(obj1)
		        .map(result -> new ResponseEntity<>(
		                result,
		                HttpStatus.OK))
		            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
		
		// getALL
		when(mocked<%= entityClass %>.getAll<%= entityClass %>s()).thenReturn(<%= entityClass.toLowerCase() %>s);

		// create
		when(mocked<%= entityClass %>.create<%= entityClass %>(obj1)).thenReturn(new ResponseEntity<>(
		        null,
		        HttpStatus.OK));
		
		// update
		when(mocked<%= entityClass %>.update<%= entityClass %>(obj1)).thenReturn(new ResponseEntity<>(
				null,
		        HttpStatus.OK));
		
		// delete
		when(mocked<%= entityClass %>.delete<%= entityClass %>(1L)).thenReturn(new ResponseEntity<>(
		        null,
		        HttpStatus.OK));
		
		RestAssuredMockMvc.standaloneSetup(mocked<%= entityClass %>);
	}
}
