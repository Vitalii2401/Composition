package com.example.composition.domain.usecases

import com.example.composition.domain.entities.Question
import com.example.composition.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {

    fun execute(maxSumValue: Int): Question =
        repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}