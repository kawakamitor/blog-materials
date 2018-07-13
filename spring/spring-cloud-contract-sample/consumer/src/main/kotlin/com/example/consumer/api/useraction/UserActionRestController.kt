package com.example.consumer.api.useraction

import com.example.common.exception.ResourceNotFoundException
import com.example.common.exception.SystemException
import com.example.consumer.repository.UserActionRepository
import com.example.provider.api.user.UserResource
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.net.URI

@RestController
@RequestMapping("action")
class UserActionRestController(val userActionRepository: UserActionRepository, val restTemplate: RestTemplate) {

    @Value("\${provider.url}")
    val providerUrl: String = "http://localhost:8081/user/"

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postUserAction(@RequestBody userActionResource: UserActionResource) {

        val responseEntity = restTemplate.getForEntity(URI.create(providerUrl + userActionResource.id), UserResource::class.java)
        if (responseEntity.statusCode == HttpStatus.NOT_FOUND) {
            throw ResourceNotFoundException("403", "ユーザID：${userActionResource.id} は存在しません.")
        }

        val userResource = responseEntity.body
        var resultCount = 0
        if (userResource != null) {
            resultCount = userActionRepository.insertUserAction(userResource.id, userResource.name, userActionResource.userAction)
        }
        if (resultCount != 1) {
            throw SystemException("500", "User action add failed. UserID：${userActionResource.id}")
        }

    }

    @GetMapping("/{id}")
    fun getUserActionById(@PathVariable("id") id: Long): List<UserActionResource> {
        return userActionRepository.selectUserActionById(id)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun userNotFound(e: ResourceNotFoundException): Map<String, String?> = mapOf(Pair("status", e.statusCode), Pair("message", e.message))

    @ExceptionHandler(SystemException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun userNotFound(e: SystemException): Map<String, String?> = mapOf(Pair("status", e.statusCode), Pair("message", e.message))

}