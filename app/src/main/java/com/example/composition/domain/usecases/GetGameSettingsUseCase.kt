package com.example.composition.domain.usecases

import com.example.composition.domain.entities.GameSettings
import com.example.composition.domain.entities.Level
import com.example.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    fun execute(level: Level): GameSettings = repository.getGameSettings(level)
}