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
			url('/api/<%= entityApiUrl %>/1')
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
			var fieldName = fields[idx].fieldName;
			
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldName %> : value(consumer('test'), producer(regex('[a-zA-Z0-9\\W]{1,255}')))<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldName %> : value(consumer(1), producer(regex('[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldName %> : value(consumer(1000), producer(regex('[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldName %> : value(consumer(10.0), producer(regex('[0-9]{1,10}.[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldName %> : value(consumer(10.0), producer(regex('[0-9]{1,10}.[0-9]{1,5}')))<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldName %> : value(consumer(0.1000000000000000), producer(regex('[0-9]{1,10}.[0-9]{1,15}')))<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldName %> : value(consumer(100000), producer(regex('[0-9]{1,15}')))<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldName %> : value(consumer([1970,01,01]), producer([1970,01,01]))<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldName %> : value(consumer('1483225200.100000000'), producer(regex('[0-9]{1,10}.[0-9]{1,9}')))<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldName %> : value(consumer(true), producer(regex(anyBoolean())))<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldName %> : value(consumer(100100100100), producer(regex('[0-1]{1,1000}')))<%=coma %>
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
            url('/api/<%= entityApiUrl %>?sort=id,desc')
			
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
			var fieldName = fields[idx].fieldName;
			
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldName %> : value(consumer('test'), producer(regex('[a-zA-Z0-9]{1,255}')))<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldName %> : value(consumer(1), producer(regex('[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldName %> : value(consumer(1000), producer(regex('[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldName %> : value(consumer(10.0), producer(regex('[0-9]{1,10}.[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldName %> : value(consumer(10.0), producer(regex('[0-9]{1,10}.[0-9]{1,5}')))<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldName %> : value(consumer(0.1000000000000000), producer(regex('[0-9]{1,10}.[0-9]{1,15}')))<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldName %> :value(consumer(100000), producer(regex('[0-9]{1,15}')))<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldName %> :value(consumer([1970,01,01]), producer([1970,01,01]))<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldName %> : value(consumer('1483225200.100000000'), producer(regex('[0-9]{1,10}.[0-9]{1,9}')))<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldName %> : value(consumer(true), producer(regex(anyBoolean())))<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldName %> : value(consumer(100100100100), producer(regex('[0-1]{1,1000}')))<%=coma %>
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
			var fieldName = fields[idx].fieldName;
			
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldName %> : value(consumer('test2'), producer(regex('[a-zA-Z0-9]{1,255}')))<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldName %> : value(consumer(2), producer(regex('[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldName %> : value(consumer(2000), producer(regex('[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldName %> : value(consumer(20.0), producer(regex('[0-9]{1,10}.[0-9]{1,10}')))<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldName %> : value(consumer(2.0), producer(regex('[0-9]{1,10}.[0-9]{1,5}')))<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldName %> : value(consumer(0.2000000000000000), producer(regex('[0-9]{1,10}.[0-9]{1,15}')))<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldName %> :value(consumer(20000), producer(regex('[0-9]{1,15}')))<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldName %> :value(consumer([1970,01,01]), producer([1970,01,01]))<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldName %> : value(consumer('1483225200.100000000'), producer(regex('[0-9]{1,10}.[0-9]{1,9}')))<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldName %> : value(consumer(true), producer(regex(anyBoolean())))<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldName %> : value(consumer(011011011011), producer(regex('[0-1]{1,1000}')))<%=coma %>
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
            url('/api/<%= entityApiUrl %>')
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
			var fieldName = fields[idx].fieldName;
			
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldName %> : value(consumer(regex('[a-zA-Z0-9]{1,255}')), producer('test'))<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}')), producer(2))<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}')), producer(2000))<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}.[0-9]{1,10}')), producer(20.0))<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}.[0-9]{1,5}')), producer(2.0))<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}.[0-9]{1,15}')), producer(0.2000000000000000))<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldName %> :value(consumer(regex('[0-9]{1,15}')), producer(20000))<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldName %> :value(consumer([1970,01,01]), producer([1970,01,01]))<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}Z')), producer('1970-01-01T00:00Z'))<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldName %> : value(consumer(regex(anyBoolean())), producer(true))<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldName %> : value(consumer(regex('[0-1]{1,1000}')), producer(011011011011))<%=coma %>
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
			url('/api/<%= entityApiUrl %>')
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
			var fieldName = fields[idx].fieldName;
			if (fieldType == 'String' || fieldTypeBlobContent == 'text') {_%>
				<%=fieldName %> : value(consumer(regex('[a-zA-Z0-9]{1,255}')), producer('test'))<%=coma %>
			<%_ } else if (fieldType == 'Integer') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}')), producer(2))<%=coma %>
			<%_ } else if (fieldType == 'Long') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}')), producer(2000))<%=coma %>
			<%_ } else if (fieldType == 'Float') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}.[0-9]{1,10}')), producer(20.0))<%=coma %>
			<%_ } else if (fieldType == 'Double') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}.[0-9]{1,5}')), producer(2.0))<%=coma %>
			<%_ } else if (fieldType == 'BigDecimal') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{1,10}.[0-9]{1,15}')), producer(0.2000000000000000))<%=coma %>
			<%_ } else if (fieldType == 'UUID') { _%>
				<%=fieldName %> :value(consumer(regex('[0-9]{1,15}')), producer(20000))<%=coma %>
			<%_ } else if (fieldType == 'LocalDate') { _%>
				<%=fieldName %> :value(consumer([1970,01,01]), producer([1970,01,01]))<%=coma %>
			<%_ } else if (fieldType == 'ZonedDateTime') { _%>
				<%=fieldName %> : value(consumer(regex('[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}Z')), producer('1970-01-01T00:00Z'))<%=coma %>
			<%_ } else if (fieldType == 'Boolean') { _%>
				<%=fieldName %> : value(consumer(regex(anyBoolean())), producer(true))<%=coma %>
			<%_ } else if ((fieldType == 'byte[]' || fieldType === 'ByteBuffer') && fieldTypeBlobContent != 'text') { _%>
				<%=fieldName %> : value(consumer(regex('[0-1]{1,1000}')), producer(011011011011))<%=coma %>
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
			url('/api/<%= entityApiUrl %>/1')
		}
		response {
			status 200
		}
	}
]