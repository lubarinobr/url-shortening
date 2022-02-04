package com.lubarino.urlshortening.services

import java.time.LocalDateTime

interface IUrlService {

    fun createURL(originalURL: String, alias: String?, expirationDate: LocalDateTime? = null, userId: Int?) : String
    fun findURL(hash: String?, originalURL: String?) : String
}