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

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.mycompany.myapp.domain.<%= entityClass %>;
import com.mycompany.myapp.repository.<%= entityClass %>Repository;
import com.mycompany.myapp.web.rest.<%= entityClass %>Resource;
import com.mycompany.myapp.service.dto.<%= entityClass %>DTO;
import com.mycompany.myapp.service.mapper.<%= entityClass %>Mapper;

/**
 * <%= entityClass %>Base is the base class for spring cloud contract.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = <%= mainClass %>.class)
public class <%= entityClass %>Base{
	
	@Inject
    private <%= entityClass %>Repository  <%= entityInstance %>Repository;

    @Inject
    private <%= entityClass %>Mapper  <%= entityInstance %>Mapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Before
    @Transactional
    public void setup() {
    	<%= entityClass %>Resource <%= entityInstance %>Resource = new <%= entityClass %>Resource();
        <%_ if (service != 'no') { _%>
        ReflectionTestUtils.setField(<%= entityInstance %>Resource, "<%= entityInstance %>Service", <%= entityInstance %>Service);
        <%_ } else { _%>
            <%_ if (searchEngine == 'elasticsearch') { _%>
        ReflectionTestUtils.setField(<%= entityInstance %>Resource, "<%= entityInstance %>SearchRepository", <%= entityInstance %>SearchRepository);
            <%_ } _%>
        ReflectionTestUtils.setField(<%= entityInstance %>Resource, "<%= entityInstance %>Repository", <%= entityInstance %>Repository);
        <%_ } _%>
        <%_ if (service == 'no' && dto == 'mapstruct') { _%>
        ReflectionTestUtils.setField(<%= entityInstance %>Resource, "<%= entityInstance %>Mapper", <%= entityInstance %>Mapper);
        <%_ } _%>
        RestAssuredMockMvc.standaloneSetup(<%= entityInstance %>Resource, new MockMvcConfigurer(){
			@Override
			public void afterConfigurerAdded(ConfigurableMockMvcBuilder<?> builder) {
				StandaloneMockMvcBuilder s = (StandaloneMockMvcBuilder) builder;
				s.setCustomArgumentResolvers(pageableArgumentResolver)
				.setMessageConverters(jacksonMessageConverter);
			}

			@Override
			public RequestPostProcessor beforeMockMvcCreated(ConfigurableMockMvcBuilder<?> builder, WebApplicationContext cxt) {
				return null;
			}
		});
    }
	
	@Transactional
	public String generateUrl(){
		
		<% var viaService = service != 'no';
		var instanceType = (dto == 'mapstruct') ? entityClass + 'DTO' : entityClass;
		var instanceName = (dto == 'mapstruct') ? entityInstance + 'DTO' : entityInstance; -%>
		// 2 objects for return/ list
		<%= instanceType %> obj1 = new <%= instanceType %>();
		obj1.setId(1L);
		<%_ 
		for (idx in fields) {
		var fieldType = fields[idx].fieldType;
		var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
		var isEnum = fields[idx].fieldIsEnum;
		var fieldNameCapitalized =  fields[idx].fieldNameCapitalized;
		if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
		obj1.set<%=fieldNameCapitalized %>("test");
		<%_ } else if (fieldType == 'Integer') { _%>
		obj1.set<%=fieldNameCapitalized %>(1);
		<%_ } else if (fieldType == 'Long') { _%>
		obj1.set<%=fieldNameCapitalized %>(1000L);
		<%_ } else if (fieldType == 'Float') { _%>
		obj1.set<%=fieldNameCapitalized %>(10.0f);
		<%_ } else if (fieldType == 'Double') { _%>
		obj1.set<%=fieldNameCapitalized %>(10.10);
		<%_ } else if (fieldType == 'BigDecimal') { _%>
		obj1.set<%=fieldNameCapitalized %>(new BigDecimal("0.1000000000000000"));
		obj2.set<%=fieldNameCapitalized %>(new BigDecimal("0.2000000000000000"));
		<%_ } else if (fieldType == 'UUID') { _%>
		obj1.set<%=fieldNameCapitalized %>(100000);
		<%_ } else if (fieldType == 'LocalDate') { _%>
		obj1.set<%=fieldNameCapitalized %>(LocalDate.ofEpochDay(0L));
		<%_ } else if (fieldType == 'ZonedDateTime') { _%>
		obj1.set<%=fieldNameCapitalized %>(ZonedDateTime.now());
		<%_ } else if (fieldType == 'Boolean') { _%>
		obj1.set<%=fieldNameCapitalized %>(true);
		<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
		obj1.set<%=fieldNameCapitalized %>(100100100100);
		<%_ }} _%>
		
		
		<%= entityClass %> <%= entityInstance %> = <%= entityInstance %>Mapper.<%= entityInstance %>DTOTo<%= entityClass %>(obj1);
		<%= entityInstance %> = <%= entityInstance %>Repository.save(<%= entityInstance %>);
		<%= entityClass %>DTO result = <%= entityInstance %>Mapper.<%= entityInstance %>To<%= entityClass %>DTO(<%= entityInstance %>);
		return "/api/"+<%= entityInstance %>+"/"+result.getId();
	}
}
