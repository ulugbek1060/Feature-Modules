package uz.bigboys.navigation.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NavigationBindsModule::class])
@InstallIn(SingletonComponent::class)
class NavigationModule {



}

