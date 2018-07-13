package com.example.provider.repository

import com.example.common.exception.ResourceNotFoundException
import com.example.provider.api.user.UserResource
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserRepository(val jdbcTemplate: NamedParameterJdbcTemplate) {

    @Transactional(readOnly = true)
    fun selectUserById(id: Long): UserResource {

        val param = MapSqlParameterSource().addValue("id", id)
        val result = jdbcTemplate.query(
                "SELECT * FROM user WHERE id=:id",
                param,
                RowMapper { rs, rowNum ->
                    UserResource(rs.getLong("id"), rs.getString("name"), rs.getInt("age"))
                })
        if (result.size == 0) {
            throw ResourceNotFoundException("403", "UserID：${id} is not exists.")
        }
        return result.get(0)

    }

}