import org.springframework.cloud.contract.spec.Contract
<%_ 
var nbFields = 0;
for (idx in fields) {
	nbFields++;
}_%>
[
	Contract.make {
		ignored()
		name("get one")
		request {
			method 'GET'
			url('/api/<%=name%>s/1')
		}
		response {
			status 200
			body([
				id : 1,
			<%_ 
			var i = 0;
			var coma = '';
			for (idx in fields) {
			i++;
			if(i<nbFields){coma = ',';}
			else {coma = '';}
			var fieldType = fields[idx].fieldType;
			var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
			var isEnum = fields[idx].fieldIsEnum;
			var fieldNameUnderscored = fields[idx].fieldNameUnderscored;
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldNameUnderscored %> : 'test'<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldNameUnderscored %> : 2<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 5065449800L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.15484585656165615265125225<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :64165116<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : <%=coma %>
			<%_ }} _%>
			])
			headers {
			contentType('application/json; charset=utf8')
			}
		}
	},
    Contract.make {
		ignored()
        name("get all")
        request {
            method 'GET'
            url('/api/<%=name%>s?sort=id,desc')
			body([
				[id : 1,
			<%_ 
			var i = 0;
			var coma = '';
			for (idx in fields) {
			i++;
			if(i<nbFields){coma = ',';}
			else {coma = '';}
			var fieldType = fields[idx].fieldType;
			var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
			var isEnum = fields[idx].fieldIsEnum;
			var fieldNameUnderscored = fields[idx].fieldNameUnderscored;
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldNameUnderscored %> : 'test'<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldNameUnderscored %> : 2<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 5065449800L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.15484585656165615265125225<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :64165116<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : <%=coma %>
			<%_ }} _%>
			],
			[id : 2,
			<%_ 
			var i = 0;
			var coma = '';
			for (idx in fields) {
			i++;
			if(i<nbFields){coma = ',';}
			else {coma = '';}
			var fieldType = fields[idx].fieldType;
			var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
			var isEnum = fields[idx].fieldIsEnum;
			var fieldNameUnderscored = fields[idx].fieldNameUnderscored;
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldNameUnderscored %> : 'test'<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldNameUnderscored %> : 5<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 5065449800L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.15484585656165615265125225<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :64165116<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : false<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : <%=coma %>
			<%_ }} _%>
			]
			
			])
        }
        response {
            status 200
        }
    },
    Contract.make {
		ignored()
        name("create")
        request {
            method 'POST'
            url('/api/<%=name%>s')
			body([
				id : 1,
			<%_ 
			var i = 0;
			var coma = '';
			for (idx in fields) {
			i++;
			if(i<nbFields){coma = ',';}
			else {coma = '';}
			var fieldType = fields[idx].fieldType;
			var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
			var isEnum = fields[idx].fieldIsEnum;
			var fieldNameUnderscored = fields[idx].fieldNameUnderscored;
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldNameUnderscored %> : 'test'<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldNameUnderscored %> : 2<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 5065449800L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.15484585656165615265125225<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :64165116<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : <%=coma %>
			<%_ }} _%>
			])
			headers {
			contentType('application/json; charset=utf8')
			}
        }
        response {
            status 200
        }
    },
	Contract.make {
		ignored()
		name("update")
		request {
			method 'PUT'
			url('/api/<%=name%>s')
			body([
				id : 1,
			<%_ 
			var i = 0;
			var coma = '';
			for (idx in fields) {
			i++;
			if(i<nbFields){coma = ',';}
			else {coma = '';}
			var fieldType = fields[idx].fieldType;
			var fieldTypeBlobContent = fields[idx].fieldTypeBlobContent;
			var isEnum = fields[idx].fieldIsEnum;
			var fieldNameUnderscored = fields[idx].fieldNameUnderscored;
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldNameUnderscored %> : 'test'<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldNameUnderscored %> : 2<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 5065449800L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.15484585656165615265125225<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :64165116<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> :<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : <%=coma %>
			<%_ }} _%>
			])
		}
		response {
			status 200
		}
	},
	Contract.make {
		ignored()
		name("delete")
		request {
			method 'DELETE'
			url('/api/<%=name%>s/1')
		}
		response {
			status 200
		}
	}
]