package com.example.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder


object SaltHasher {
    private val encoder: PasswordEncoder = BCryptPasswordEncoder()

    fun hashString(string: String): String = encoder.encode(string)

    fun compareHash(string: String, hash: String): Boolean = encoder.matches(string, hash)
}