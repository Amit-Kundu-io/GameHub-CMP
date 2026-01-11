package amitkundu.database.di

import amitkundu.database.AppDatabase
import amitkundu.database.DatabaseFactory
import org.koin.dsl.module

fun getDatabaseModule()  = module {

    single<AppDatabase> {
        get<DatabaseFactory>().create()
    }

    single {
        get<AppDatabase>().gameDao()
    }
}