package com.example.iub.services

import com.example.iub.services.contracts.JWTService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtServiceImpl(
    @Value("\${security.jwt.secret}") private val secret: String,
    @Value("\${security.jwt.expiration}") private val expirationMs: Long,
    @Value("\${security.jwt.issuer}") private val issuer: String
) : JWTService {

    private fun getSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.toByteArray())
    }

    override fun generateToken(uid: Int, email: String, role: String): String {
        val now = Date()
        val exp = Date(now.time + expirationMs)
        return Jwts.builder()
            .setIssuer(issuer)
            .setSubject(email)
            .claim("uid", uid)
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(exp)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    override fun validate(token: String): Boolean = runCatching {
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
    }.isSuccess

    override fun getEmail(token: String): String =
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).body.subject

    override fun getUserId(token: String): Int =
        (Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).body["uid"] as Number).toInt()

    override fun getRole(token: String): String =
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).body["role"] as String
}
