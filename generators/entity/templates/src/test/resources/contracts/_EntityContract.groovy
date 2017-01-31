org.springframework.cloud.contract.spec.Contract.make {
	ignored() // ignoring this contract, jhipster is doing the test !
	request {
		method 'GET'
		url '/api/<%=name%>s'
		body([
			<%_ for (idx in fields) {
				var fieldType = fields[idx].fieldType;
				var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
				var isEnum = fields[idx].fieldIsEnum;
				var fieldNameUnderscored = fields[idx].fieldNameUnderscored;
				if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
					'<%=fieldNameUnderscored %>' : 'test'
				<%_ } else if (fieldType == 'Integer') { _%>
					'<%=fieldNameUnderscored %>' : 2
				<%_ } else if (fieldType == 'Long') { _%>
					'<%=fieldNameUnderscored %>' : 5065449800L
				<%_ } else if (fieldType == 'Float') { _%>
					'<%=fieldNameUnderscored %>' : 10.0f
				<%_ } else if (fieldType == 'Double') { _%>
					'<%=fieldNameUnderscored %>' : 10.10
				<%_ } else if (fieldType == 'BigDecimal') { _%>
					'<%=fieldNameUnderscored %>' : 
				<%_ } else if (fieldType == 'UUID') { _%>
					'<%=fieldNameUnderscored %>' :64165116
				<%_ } else if (fieldType == 'LocalDate') { _%>
					'<%=fieldNameUnderscored %>' :
				<%_ } else if (fieldType == 'ZonedDateTime') { _%>
					'<%=fieldNameUnderscored %>' :
				<%_ } else if (fieldType == 'Boolean') { _%>
					'<%=fieldNameUnderscored %>' : true
				<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
					'<%=fieldNameUnderscored %>' : 
				<%_ } } _%>
		])
		headers {
			contentType('application/json; charset=utf8')
		}
	}
	response {
		status 200
		body([
				body: "bar"
		])
	}
}