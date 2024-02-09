package com.root14.passwordgenerator.di

import android.content.Context
import com.root14.passwordgenerator.util.ClipBoardUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class BaseModule {

    @Singleton
    @Provides
    fun provideCopyBoardUtil(@ApplicationContext context: Context) = ClipBoardUtil(context)
}
