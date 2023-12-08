package com.example.picpeek.di

import com.example.picpeek.data.repo.Repo
import com.example.picpeek.data.repo.RepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepo(repoImp: RepoImp): Repo

}