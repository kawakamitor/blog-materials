package com.example.gatlingsampleapi.controller

import com.example.gatlingsampleapi.data.GatlingSampleData
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GatlingSampleController {

    private val logger = LoggerFactory.getLogger(GatlingSampleController::class.java)

    @GetMapping("{pathVariable}")
    fun get(@PathVariable pathVariable: Int, @RequestParam requestParam: Int): GatlingSampleData {

        logger.info("pathVariable is ${pathVariable} and requestParam is ${requestParam}.")

        pathVariable % requestParam

        val sleepTime = pathVariable * 100
        logger.info("Sleep ${sleepTime} ms.")
        Thread.sleep(sleepTime.toLong())

        return GatlingSampleData(pathVariable, requestParam)
    }

}