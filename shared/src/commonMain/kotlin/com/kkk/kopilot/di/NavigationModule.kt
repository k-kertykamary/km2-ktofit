package com.kkk.kopilot.di

import com.kkk.kopilot.navigation.LibraryNavigator
import com.kkk.kopilot.navigation.LibraryNavigatorImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val navigationModule = module {
    single { LibraryNavigatorImpl() } bind LibraryNavigator::class
}