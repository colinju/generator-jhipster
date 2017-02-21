package <%=packageName%>;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.net.URISyntaxException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.domain.Pageable;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import <%=packageName%>.domain.<%= entityClass %>;
import <%=packageName%>.web.rest.<%= entityClass %>Resource;
import <%=packageName%>.service.dto.<%= entityClass %>DTO;

/**
 * <%= entityClass %>Base is the base class for spring cloud contract.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = <%= mainClass %>.class)
public class <%= entityClass %>Base{
	
	@Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
	
	@Before
	public void setup() throws URISyntaxException {
		
		Configuration.setDefaults(new Configuration.Defaults() {
		    private final JsonProvider jsonProvider = new JacksonJsonProvider();
		    private final MappingProvider mappingProvider = new JacksonMappingProvider();

		    @Override
		    public JsonProvider jsonProvider() {
		        return jsonProvider;
		    }

		    @Override
		    public MappingProvider mappingProvider() {
		        return mappingProvider;
		    }

		    @Override
		    public Set<Option> options() {
		        return EnumSet.noneOf(Option.class);
		    }
		});
		
		// mock resource
		<%_ if (pagination != 'no') { _%>
		Pageable pageable = mock(Pageable.class);
		<%_ } _%>
		<%= entityClass %>Resource mocked<%= entityClass %> = mock(<%= entityClass %>Resource.class);
		
		<% var viaService = service != 'no';
		var instanceType = (dto == 'mapstruct') ? entityClass + 'DTO' : entityClass;
		var instanceName = (dto == 'mapstruct') ? entityInstance + 'DTO' : entityInstance; -%>
		// 2 objects for return/ list
		<%= instanceType %> obj1 = new <%= instanceType %>(),obj2 = new <%= instanceType %>();
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
		obj1.set<%=fieldNameCapitalized %>(new BigDecimal("0.1000000000000000"));
		obj2.set<%=fieldNameCapitalized %>(new BigDecimal("0.2000000000000000"));
		<%_ } else if (fieldType == 'UUID') { _%>
		obj1.set<%=fieldNameCapitalized %>(100000);
		obj2.set<%=fieldNameCapitalized %>(200000);
		<%_ } else if (fieldType == 'LocalDate') { _%>
		obj1.set<%=fieldNameCapitalized %>(LocalDate.ofEpochDay(0L));
		obj2.set<%=fieldNameCapitalized %>(LocalDate.ofEpochDay(0L));
		<%_ } else if (fieldType == 'ZonedDateTime') { _%>
		obj1.set<%=fieldNameCapitalized %>(ZonedDateTime.now());
		obj2.set<%=fieldNameCapitalized %>(ZonedDateTime.now());
		<%_ } else if (fieldType == 'Boolean') { _%>
		obj1.set<%=fieldNameCapitalized %>(true);
		obj2.set<%=fieldNameCapitalized %>(false);
		<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
		obj1.set<%=fieldNameCapitalized %>(100100100100);
		obj2.set<%=fieldNameCapitalized %>(011011011011);
		<%_ }} _%>		
		
		// entity list for get all
		List<<%= entityClass %>DTO> <%= entityInstancePlural %> = new ArrayList<<%= entityClass %>DTO>();
		<%= entityInstancePlural %>.add(obj1);
		<%= entityInstancePlural %>.add(obj2);
		
		// get
		when(mocked<%= entityClass %>.get<%= entityClass %>(1L)).thenReturn(Optional.ofNullable(obj1)
		        .map(result -> new ResponseEntity<>(
		                result,
		                HttpStatus.OK))
		            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
		
		// getALL
		<%_ if (pagination == 'no') { _%>
		when(mocked<%= entityClass %>.getAll<%= entityClassPlural %>()).thenReturn(<%= entityInstancePlural %>);
		<%_ }else if (pagination != 'no') { _%>
		when(mocked<%= entityClass %>.getAll<%= entityClassPlural %>(pageable)).thenReturn(
				Optional.ofNullable(obj1)
		        .map(result -> new ResponseEntity<>(
		        		<%= entityInstancePlural %>,
		                HttpStatus.OK))
		            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
		<%_ } _%>
		
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
				

		RestAssuredMockMvc.standaloneSetup(mocked<%= entityClass %>, new MockMvcConfigurer(){
			@Override
			public void afterConfigurerAdded(ConfigurableMockMvcBuilder<?> builder) {
				StandaloneMockMvcBuilder s = (StandaloneMockMvcBuilder) builder;
				s.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setMessageConverters(jacksonMessageConverter);
			}

			@Override
			public RequestPostProcessor beforeMockMvcCreated(ConfigurableMockMvcBuilder<?> builder, WebApplicationContext cxt) {
				return null;
			}
		});
		
		//RestAssuredMockMvc.standaloneSetup(mocked<%= entityClass %>);
	}
}
