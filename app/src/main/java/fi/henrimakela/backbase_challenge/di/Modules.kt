package fi.henrimakela.backbase_challenge.di

import fi.henrimakela.backbase_challenge.repository.CityRepository
import org.koin.dsl.module

val applicationModule = module(override = true) {
    single {
        CityRepository()
    }
}