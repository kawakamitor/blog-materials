package com.example.provider.api.user

import com.example.common.exception.ResourceNotFoundException
import com.example.provider.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserRestController(val userRepository: UserRepository) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long): UserResource {

        return userRepository.selectUserById(id)

    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun userNotFound(e: ResourceNotFoundException): Map<String, String?> = mapOf(Pair("status", e.statusCode), Pair("message", e.message))

}

