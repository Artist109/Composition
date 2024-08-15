package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

object GameRepositoryIml:GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1
    override fun generateQuestion(maxSumValue: Int, countOfVariants: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val variants = HashSet<Int>()
        variants.add(rightAnswer)
        val from = max(rightAnswer - countOfVariants, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfVariants)
        while (variants.size < countOfVariants) {
            variants.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, variants.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when(level) {
            Level.TEST -> GameSettings(
                10,
                3
                ,50
                ,8
            )
            Level.EASY -> GameSettings(
                10,
                10
                ,70
                ,60
            )
            Level.NORMAL -> GameSettings(
                20,
                20
                ,80
                ,40
            )
            Level.HARD -> GameSettings(
                30,
                30
                ,90
                ,40
            )
        }
    }
}