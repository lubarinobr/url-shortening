package com.lubarino.urlshortening.repositories

import com.lubarino.urlshortening.domains.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<Url, String> {
    fun findByHashContaining(alias: String) : Url?
    fun findByHashOrOriginalUrl(hash: String?, originalURL: String?) : Url?
}
