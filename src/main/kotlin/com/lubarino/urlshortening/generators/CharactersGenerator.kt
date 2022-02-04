package com.lubarino.urlshortening.generators

class CharactersGenerator {

    fun generate(length: Int) : String {
        val allowed = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowed.random() }
            .joinToString("")
    }
}