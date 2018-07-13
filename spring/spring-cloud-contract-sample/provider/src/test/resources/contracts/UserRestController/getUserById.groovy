package contracts.UserRestController

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url $(consumer(regex('/user/[1-5]{1}')))
    }
    response {
        status 200
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
        body(
                "id": $(producer(regex('[0-9]{1}'))),
                "name": $(producer(regex('[a-zA-Z]{1,255}'))),
                "age": $(producer(regex('[0-9]{1,2}')))
        )
    }
}
