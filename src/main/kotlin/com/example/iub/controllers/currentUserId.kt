package com.example.iub.controllers

import org.springframework.security.core.Authentication

fun currentUserId(auth: Authentication): Int =
    ((auth.details as? Map<*, *>)?.get("uid") as? Number)?.toInt()
        ?: error("UID not found in authentication details")