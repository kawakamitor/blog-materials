package com.example.springprofilessample.repository

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class SampleRepository(val jdbcTemplate: NamedParameterJdbcTemplate) {

    @Transactional
    fun insert(id: Int): Int {
        val param = MapSqlParameterSource()
                .addValue("id", id)
        return jdbcTemplate.update("INSERT INTO sample (id) VALUES (:id)", param)
    }

}