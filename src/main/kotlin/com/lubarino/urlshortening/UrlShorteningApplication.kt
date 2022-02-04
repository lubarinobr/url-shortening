package com.lubarino.urlshortening

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShorteningApplication

fun main(args: Array<String>) {
	runApplication<UrlShorteningApplication>(*args)
}
