import org.springframework.cloud.contract.spec.Contract
<%_ 
var nbFields = 0;
for (idx in fields) {
	nbFields++;
}_%>
[
	Contract.make {
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
				<%=fieldNameUnderscored %> : 1<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 1000L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.1000000000000000<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :100000<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01' <%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01T00:00Z'<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : 100100100100<%=coma %>
			<%_ }} _%>
			])
			headers {
				contentType('application/json;charset=UTF-8')
			}
		}
	},
    Contract.make {
        name("get all")
        request {
            method 'GET'
            url('/api/<%=name%>s?sort=id,desc')
			
        }
        response {
            status 200
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
				<%=fieldNameUnderscored %> : 1<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 1000L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.1000000000000000<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :100000<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01' <%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01T00:00Z'<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : 100100100100<%=coma %>
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
				<%=fieldNameUnderscored %> : 'test2'<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldNameUnderscored %> : 2<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 2000L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 20.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 20.20<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.200000000000000<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :200000<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01' <%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01T00:00Z'<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : 01101101101<%=coma %>
			<%_ }} _%>
			]
			
			])
			headers {
				contentType('application/json;charset=UTF-8')
			}
        }
    },
    Contract.make {
        name("create")
        request {
            method 'POST'
            url('/api/<%=name%>s')
			body([
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
				<%=fieldNameUnderscored %> : 1<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 1000L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.1000000000000000<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :100000<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01' <%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01T00:00Z'<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : 100100100100<%=coma %>
			<%_ }} _%>
			])
			headers {
				contentType('application/json;charset=UTF-8')
			}
        }
        response {
            status 200
        }
    },
	Contract.make {
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
				<%=fieldNameUnderscored %> : 1<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldNameUnderscored %> : 1000L<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldNameUnderscored %> : 10.0f<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldNameUnderscored %> : 10.10<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldNameUnderscored %> : 0.1000000000000000<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldNameUnderscored %> :100000<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01' <%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldNameUnderscored %> : '1970-01-01T00:00Z'<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldNameUnderscored %> : true<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldNameUnderscored %> : 100100100100<%=coma %>
			<%_ }} _%>
			])
			headers {
				contentType('application/json;charset=UTF-8')
			}
		}
		response {
			status 200
		}
	},
	Contract.make {
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