org.springframework.cloud.contract.spec.Contract.make {
	request {
		method 'GET'
		url '/api/<%=name%>s'
		body([
			<%_ for (idx in fields) {
				var fieldType = fields[idx].fieldType;
				var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
				var isEnum = fields[idx].fieldIsEnum;
				if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
					
				<%_ } else if (fieldType == 'Integer') { _%>
				
				<%_ } else if (fieldType == 'Long') { _%>
				
				<%_ } else if (fieldType == 'Float') { _%>

				<%_ } else if (fieldType == 'Double') { _%>

				<%_ } else if (fieldType == 'BigDecimal') { _%>

				<%_ } else if (fieldType == 'UUID') { _%>

				<%_ } else if (fieldType == 'LocalDate') { _%>

				<%_ } else if (fieldType == 'ZonedDateTime') { _%>

				<%_ } else if (fieldType == 'Boolean') { _%>

				<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>

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