package com.example.iub.exceptions

import java.time.LocalDateTime

data class ApiException(
    val status: Int,
    val message: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)
