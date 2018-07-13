package com.example.consumer.repository

import com.example.consumer.api.useraction.UserActionResource
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
class UserActionRepository(val jdbcTemplate: NamedParameterJdbcTemplate) {

    @Transactional
    fun insertUserAction(id: Long, name: String, userAction: String): Int {

        val param = MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name)
                .addValue("createAt", Date())
                .addValue("userAction", userAction)
        return jdbcTemplate.update("INSERT INTO user_action (id, name, createAt, user_action) VALUES (:id, :name, :createAt, :userAction)", param)

    }

    @Transactional(readOnly = true)
    fun selectUserActionById(id: Long): List<UserActionResource> {
        val param = MapSqlParameterSource()
                .addValue("id", id)
        return jdbcTemplate.query("SELECT * FROM user_action WHERE id = :id ORDER BY createAt desc", param,
                RowMapper { rs, rowNum ->
                    UserActionResource(rs.getLong("id"), rs.getString("name"), rs.getTimestamp("createAt"), rs.getString("user_action"))
                })
    }

}