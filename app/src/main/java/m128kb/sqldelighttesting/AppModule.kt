package m128kb.sqldelighttesting

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext application: Context): MyApplication {
        return application as MyApplication
    }

    @Provides
    @Singleton
    fun provideDriver(@ApplicationContext application: Context): SqlDriver {
        return AndroidSqliteDriver(
            schema = MyDB.Schema,
            context = application,
            name = "MyDB.db"
        )
    }


    @Provides
    @Singleton
    fun providePersonDataSource(driver: SqlDriver): PersonDateSource {
        return PersonDataSourceImpl(MyDB(driver))
    }


}


//val appModule = module {
//
//    single {
//        AndroidSqliteDriver(
//            schema = MyDB.Schema,
//            context = MyApplication().applicationContext,
//            name = "MyDB.db",
//        )
//    }
//
//
//    single<PersonDateSource> {
//        PersonDataSourceImpl(MyDB(get()))
//    }
//
//    single {
//        PersonViewModel(get())
//    }
//
//
//}