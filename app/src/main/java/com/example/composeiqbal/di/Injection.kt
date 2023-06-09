package com.example.composeiqbal.di

import com.example.composeiqbal.Repo.TokohPolitikRepository

object Injection {
    fun provideRepository(): TokohPolitikRepository {
        return TokohPolitikRepository.getInstance()
    }
}