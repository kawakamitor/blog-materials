package com.example.springprofilessample.api.sample

import com.example.springprofilessample.repository.SampleRepository
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController("/")
class SampleRestController(val sampleRepository: SampleRepository) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postSample(@RequestBody sampleResource: SampleResource) {
        sampleRepository.insert(sampleResource.id)
    }

}

@RestController("profiles")
class ProfileRestController(val environment: Environment) {

    @GetMapping
    fun getProfile(): Array<String> {
        return environment.activeProfiles
    }

}