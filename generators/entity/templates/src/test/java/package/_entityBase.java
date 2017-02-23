package <%=packageName%>;

<% if (databaseType == 'cassandra') { %>
import <%=packageName%>.AbstractCassandraTest;<% } %>
import <%=packageName%>.<%= mainClass %>;
<% if (authenticationType == 'uaa') { %>
import <%=packageName%>.config.SecurityBeanOverrideConfiguration;
<% } %>
import <%=packageName%>.domain.<%= entityClass %>;
<%_ for (idx in relationships) { // import entities in required relationships
        var relationshipValidate = relationships[idx].relationshipValidate;
        var otherEntityNameCapitalized = relationships[idx].otherEntityNameCapitalized;
        if (relationshipValidate != null && relationshipValidate === true) { _%>
import <%=packageName%>.domain.<%= otherEntityNameCapitalized %>;
<%_ } } _%>
import <%=packageName%>.repository.<%= entityClass %>Repository;<% if (service != 'no') { %>
import <%=packageName%>.service.<%= entityClass %>Service;<% } if (searchEngine == 'elasticsearch') { %>
import <%=packageName%>.repository.search.<%= entityClass %>SearchRepository;<% } if (dto == 'mapstruct') { %>
import <%=packageName%>.service.dto.<%= entityClass %>DTO;
import <%=packageName%>.service.mapper.<%= entityClass %>Mapper;<% } %>
import <%=packageName%>.web.rest.<%= entityClass %>Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;<% if (databaseType == 'sql') { %>
import org.springframework.transaction.annotation.Transactional;<% } %><% if (fieldsContainBlob == true) { %>
import org.springframework.util.Base64Utils;<% } %>
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;

import javax.inject.Inject;<% if (databaseType == 'sql') { %>
import javax.persistence.EntityManager;<% } %><% if (fieldsContainLocalDate == true) { %>
import java.time.LocalDate;<% } %><% if (fieldsContainZonedDateTime == true) { %>
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;<% } %><% if (fieldsContainLocalDate == true || fieldsContainZonedDateTime == true) { %>
import java.time.ZoneId;<% } %><% if (fieldsContainBigDecimal == true) { %>
import java.math.BigDecimal;<% } %><% if (fieldsContainBlob == true && databaseType === 'cassandra') { %>
import java.nio.ByteBuffer;<% } %>
import java.util.List;<% if (databaseType == 'cassandra') { %>
import java.util.UUID;<% } %>


<%_ for (idx in fields) { if (fields[idx].fieldIsEnum == true) { _%>import <%=packageName%>.domain.enumeration.<%= fields[idx].fieldType %>;
<%_ } } _%>
/**
 * <%= entityClass %>Base is the base class for spring cloud contract.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = <%= mainClass %>.class)
<% if (databaseType == 'sql') { %>
@Transactional
<%_ } _%>
public class <%= entityClass %>Base{
	<%_ for (idx in fields) {
    var defaultValueName = 'DEFAULT_' + fields[idx].fieldNameUnderscored.toUpperCase();
    
    var defaultValue = 1;
    
    if (fields[idx].fieldValidate == true) {
        if (fields[idx].fieldValidateRules.indexOf('max') != -1) {
            defaultValue = fields[idx].fieldValidateRulesMax;
        }
        if (fields[idx].fieldValidateRules.indexOf('min') != -1) {
            defaultValue = fields[idx].fieldValidateRulesMin;
        }
        if (fields[idx].fieldValidateRules.indexOf('minbytes') != -1) {
            defaultValue = fields[idx].fieldValidateRulesMinbytes;
        }
        if (fields[idx].fieldValidateRules.indexOf('maxbytes') != -1) {
            updatedValue = fields[idx].fieldValidateRulesMaxbytes;
        }
    }

    var fieldType = fields[idx].fieldType;
    var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
    var isEnum = fields[idx].fieldIsEnum;
    var enumValue1;
    var enumValue2;
    if (isEnum) {
        var values = fields[idx].fieldValues.replace(/\s/g, '').split(',');
        enumValue1 = values[0];
        if (values.length > 1) {
            enumValue2 = values[1];
        } else {
            enumValue2 = enumValue1;
        }
    }

    if (fieldType == 'String' || fieldTypeBlobContent == 'text') {
        // Generate Strings, using the min and max string length if they are configured
        var sampleTextString = "";
        var updatedTextString = "";
        var sampleTextLength = 10;
        if (fields[idx].fieldValidateRulesMinlength > sampleTextLength) {
            sampleTextLength = fields[idx].fieldValidateRulesMinlength;
        }
        if (fields[idx].fieldValidateRulesMaxlength < sampleTextLength) {
            sampleTextLength = fields[idx].fieldValidateRulesMaxlength;
        }
        for (var i = 0; i < sampleTextLength; i++ ) {
            sampleTextString += "A";
            updatedTextString += "B";
        }_%>

    private static final String <%=defaultValueName %> = "<%=sampleTextString %>";
    <%_ } else if (fieldType == 'Integer') { _%>

    private static final Integer <%=defaultValueName %> = <%= defaultValue %>;
    <%_ } else if (fieldType == 'Long') { _%>

    private static final Long <%=defaultValueName %> = <%= defaultValue %>L;
    <%_ } else if (fieldType == 'Float') { _%>

    private static final <%=fieldType %> <%=defaultValueName %> = <%= defaultValue %>F;
    <%_ } else if (fieldType == 'Double') { _%>

    private static final <%=fieldType %> <%=defaultValueName %> = <%= defaultValue %>D;
    <%_ } else if (fieldType == 'BigDecimal') { _%>

    private static final BigDecimal <%=defaultValueName %> = new BigDecimal(<%= defaultValue %>);
    <%_ } else if (fieldType == 'UUID') { _%>

    private static final UUID <%=defaultValueName %> = UUID.randomUUID();
    <%_ } else if (fieldType == 'LocalDate') { _%>

    private static final LocalDate <%=defaultValueName %> = LocalDate.ofEpochDay(0L);
    <%_ } else if (fieldType == 'ZonedDateTime') { _%>

    private static final ZonedDateTime <%=defaultValueName %> = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    <%_ } else if (fieldType == 'Boolean') { _%>

    private static final Boolean <%=defaultValueName %> = false;
    <%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>

    <%_ if (databaseType !== 'cassandra') { _%>
    private static final byte[] <%=defaultValueName %> = TestUtil.createByteArray(<%= defaultValue %>, "0");
    <%_ } else { _%>
    private static final ByteBuffer <%=defaultValueName %> = ByteBuffer.wrap(TestUtil.createByteArray(<%= defaultValue %>, "0"));
    <%_ } _%>
    private static final String <%=defaultValueName %>_CONTENT_TYPE = "image/jpg";
    <%_ } else if (isEnum) { _%>

    private static final <%=fieldType %> <%=defaultValueName %> = <%=fieldType %>.<%=enumValue1 %>;
    <%_ } } _%>
	
    @Inject
    private <%= entityClass %>Repository <%= entityInstance %>Repository;<% if (dto == 'mapstruct') { %>

    @Inject
    private <%= entityClass %>Mapper <%= entityInstance %>Mapper;<% } if (service != 'no') { %>

    @Inject
    private <%= entityClass %>Service <%= entityInstance %>Service;<% } if (searchEngine == 'elasticsearch') { %>

    @Inject
    private <%= entityClass %>SearchRepository <%= entityInstance %>SearchRepository;<% } %>

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
	
<%_ if (databaseType == 'sql') { _%>

    @Inject
    private EntityManager em;
<%_ } _%>

    @Before
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
	
    public static <%= entityClass %> createEntity(<% if (databaseType == 'sql') { %>EntityManager em<% } %>) {
        <%_ if (fluentMethods) { _%>
        <%= entityClass %> <%= entityInstance %> = new <%= entityClass %>()<% for (idx in fields) { %>
                .<%= fields[idx].fieldName %>(<%='DEFAULT_' + fields[idx].fieldNameUnderscored.toUpperCase()%>)<% if ((fields[idx].fieldType == 'byte[]' || fields[idx].fieldType === 'ByteBuffer') && fields[idx].fieldTypeBlobContent != 'text') { %>
                .<%= fields[idx].fieldName %>ContentType(<%='DEFAULT_' + fields[idx].fieldNameUnderscored.toUpperCase()%>_CONTENT_TYPE)<% } %><% } %>;
        <%_ } else { _%>
        <%= entityClass %> <%= entityInstance %> = new <%= entityClass %>();
            <%_ for (idx in fields) { _%>
        <%= entityInstance %>.set<%= fields[idx].fieldInJavaBeanMethod %>(<%='DEFAULT_' + fields[idx].fieldNameUnderscored.toUpperCase() %>);
                <%_ if ((fields[idx].fieldType == 'byte[]' || fields[idx].fieldType === 'ByteBuffer') && fields[idx].fieldTypeBlobContent != 'text') { _%>
        <%= entityInstance %>.set<%= fields[idx].fieldInJavaBeanMethod %>ContentType(<%='DEFAULT_' + fields[idx].fieldNameUnderscored.toUpperCase() %>_CONTENT_TYPE);
                <%_ } _%>
            <%_ } _%>
        <%_ } _%>
        <%_ for (idx in relationships) {
            var relationshipValidate = relationships[idx].relationshipValidate;
            var otherEntityNameCapitalized = relationships[idx].otherEntityNameCapitalized;
            var relationshipFieldName = relationships[idx].relationshipFieldName;
            var relationshipType = relationships[idx].relationshipType;
            var relationshipNameCapitalizedPlural = relationships[idx].relationshipNameCapitalizedPlural;
            var relationshipNameCapitalized = relationships[idx].relationshipNameCapitalized;
            if (relationshipValidate != null && relationshipValidate === true) { _%>
        // Add required entity
        <%= otherEntityNameCapitalized %> <%= relationshipFieldName %> = <%= otherEntityNameCapitalized %>ResourceIntTest.createEntity(em);
        em.persist(<%= relationshipFieldName %>);
        em.flush();
            <%_ if (relationshipType == 'many-to-many') { _%>
        <%= entityInstance %>.get<%= relationshipNameCapitalizedPlural %>().add(<%= relationshipFieldName %>);
            <%_ } else { _%>
        <%= entityInstance %>.set<%= relationshipNameCapitalized %>(<%= relationshipFieldName %>);
            <%_ } _%>
        <%_ } } _%>
        return <%= entityInstance %>;
    }
	
	
	public String urlGetAndDelete(){
		
		<%= entityClass %> obj1 =createEntity(em);	
		<%= entityClass %> <%= entityInstance %> = <%= entityInstance %>Repository.save(obj1);
		<%= entityClass %>DTO result = <%= entityInstance %>Mapper.<%= entityInstance %>To<%= entityClass %>DTO(<%= entityInstance %>);
		
		return "/api/<%= entityApiUrl %>/"+result.getId();
	}
	
	public String urlGetAll(){
		
		<%= entityClass %> obj1 =createEntity(em);
		<%= entityInstance %>Repository.save(obj1);
		<%= entityClass %> obj2 =createEntity(em);
		<%= entityInstance %>Repository.save(obj2);
		
		return "/api/<%= entityApiUrl %>?sort=id,desc";
	}
}
