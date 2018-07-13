package com.example.consumer.api.useraction

import java.sql.Timestamp

data class UserActionResource(val id: Long, val name: String?, val createAt: Timestamp?, val userAction: String)