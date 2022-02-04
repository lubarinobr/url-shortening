package com.lubarino.urlshortening

import com.lubarino.urlshortening.exceptions.AlreadyExistsException
import com.lubarino.urlshortening.fixture.URLFixture.validURL
import com.lubarino.urlshortening.repositories.UrlRepository
import com.lubarino.urlshortening.services.URLService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class URLServiceTest {

    @InjectMocks
    private lateinit var urlService: URLService

    @Mock
    private lateinit var urlRepository: UrlRepository

    @DisplayName("Create a new URL with the alias")
    @Test
    fun createANewURLWithAlias() {
        Mockito.`when`(urlRepository.findByHashContaining(CUSTOM_ALIAS)).thenReturn(validURL(FAKE_URL, CUSTOM_ALIAS))
        val createdURL = urlService.createURL(FAKE_URL, CUSTOM_ALIAS, null, null)
        Assertions.assertNotNull(createdURL)
        Assertions.assertEquals("""$BASE_URL$CUSTOM_ALIAS""", createdURL)
        verify(urlRepository, times(1)).findByHashContaining(CUSTOM_ALIAS)
    }

    @DisplayName("Should create a new URL with a custom alias")
    @Test
    fun createANewURLWithouAlias() {
        val createdURL = urlService.createURL(FAKE_URL, null, null, null)
        Assertions.assertTrue(createdURL.isNotBlank())
        verify(urlRepository, times(0)).findByHashContaining(CUSTOM_ALIAS)
    }

    @DisplayName("Should throw a error if the alias already exists")
    @Test
    fun errorWithAliasThatAlreadyExist() {
        Mockito.`when`(urlRepository.findByHashContaining(CUSTOM_ALIAS)).thenReturn(null)
        Assertions.assertThrows(AlreadyExistsException::class.java) {
            urlService.createURL(FAKE_URL, CUSTOM_ALIAS, null, null)
        }
        verify(urlRepository, times(1)).findByHashContaining(CUSTOM_ALIAS)
    }

    companion object {
        const val FAKE_URL: String = "www.google.com"
        const val CUSTOM_ALIAS: String = "teste"
        const val BASE_URL = "http://shorturl.by/"
    }
}