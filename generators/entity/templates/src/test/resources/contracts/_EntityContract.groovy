org.springframework.cloud.contract.spec.Contract.make {
	request {
		method 'GET'
		url '/api/<%=name%>s'
		body([
			body: "foo"
		])
	}
	response {
		status 200
		body([
				body: "bar"
		])
	}
}