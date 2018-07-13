package com.example.common.exception

class ResourceNotFoundException(val statusCode: String, message: String? = null) : RuntimeException(message)
class SystemException(val statusCode: String, message: String? = null) : RuntimeException(message)