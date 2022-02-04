package com.lubarino.urlshortening.services

import com.lubarino.urlshortening.domains.Url
import com.lubarino.urlshortening.exceptions.AlreadyExistsException
import com.lubarino.urlshortening.generators.CharactersGenerator
import com.lubarino.urlshortening.repositories.UrlRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class URLService(
    private val urlRepository: UrlRepository
): IUrlService {

    override fun createURL(
        originalURL: String,
        alias: String?,
        expirationDate: LocalDateTime?,
        userId: Int?
    ) : String {
        alias?.let {
            val existAlias = existAlias(it)
            require(existAlias) {
                throw AlreadyExistsException()
            }
        }

        val customURL = createCustomURL(alias)
        val url = Url(originalURL).apply { this.hash = customURL }
        urlRepository.save(url)

        return customURL
    }

    private fun createCustomURL(alias: String?) : String {
        return if (alias != null) {
            """$BASE_URL$alias"""
        } else {
            val generated = CharactersGenerator().generate(6)
            """$BASE_URL$generated"""
        }

    }

    override fun findURL(hash: String?, originalURL: String?) : String {
        TODO("Not yet implemented")
    }

    private fun existAlias(alias: String): Boolean {
        return urlRepository.findByHashContaining(alias) != null
    }

    companion object {
        const val BASE_URL = "http://shorturl.by/"
    }

}