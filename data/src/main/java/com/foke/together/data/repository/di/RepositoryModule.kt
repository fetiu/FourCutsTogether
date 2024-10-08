package com.foke.together.data.repository.di

import com.foke.together.data.repository.AppPreferencesRepository
import com.foke.together.domain.output.AppPreferenceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAppPreferenceRepository(appPreferenceRepository: AppPreferencesRepository): AppPreferenceInterface
}