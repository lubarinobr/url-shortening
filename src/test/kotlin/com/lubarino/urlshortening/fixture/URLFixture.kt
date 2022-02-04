package com.lubarino.urlshortening.fixture

import com.lubarino.urlshortening.domains.Url
import java.time.LocalDateTime

object URLFixture {

    fun validURL(url: String, alias: String): Url = Url(
        originalUrl = url,
        expirationDate = LocalDateTime.MAX,
        userId = null
    ).apply {
        this.hash = """$url$alias"""
    }
}