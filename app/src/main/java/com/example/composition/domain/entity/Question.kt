package com.example.composition.domain.entity

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val variants: List<Int>
) {
    val rightAnswer: Int
        get() = sum - visibleNumber
}