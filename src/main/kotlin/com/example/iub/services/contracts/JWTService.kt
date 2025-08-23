package com.example.iub.services.contracts

interface JWTService {
    fun generateToken(uid: Int, email: String, role: String): String
    fun validate(token: String): Boolean
    fun getEmail(token: String): String
    fun getUserId(token: String): Int
    fun getRole(token: String): String
}
