package com.rdissi.mytest.di

import com.rdissi.mytest.data.repository.CatalogRepositoryImpl
import com.rdissi.mytest.domain.repository.CatalogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModuleModule {

    @Singleton
    @Binds
    abstract fun bindCatalogRepository(impl: CatalogRepositoryImpl): CatalogRepository
}