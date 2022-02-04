package com.lubarino.urlshortening.domains

import java.time.LocalDateTime

data class Url(
    private val originalUrl: String,
    private val creationDate: LocalDateTime = LocalDateTime.now(),
    private val expirationDate: LocalDateTime? = LocalDateTime.MAX,
    private val userId: Int? = null,
) {
    lateinit var hash: String
}