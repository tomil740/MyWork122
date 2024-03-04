package com.example.mywork122.di

import android.app.Application
import android.content.Context
import com.example.mywork122.data.localDbFactory.DatabaseDriverFactory
import com.example.mywork122.data.repository.RepositoryImpl
import com.example.mywork122.database.WorkDatabase
import com.example.mywork122.domain.repository.Repository
import com.example.mywork122.domain.usecase.AddEditUsecase
import com.example.mywork122.domain.usecase.AllUseCase
import com.example.mywork122.domain.usecase.CalculateWeekSum
import com.example.mywork122.domain.usecase.EntryUseCase
import com.example.mywork122.domain.usecase.GetDaySumByDate
import com.example.mywork122.domain.usecase.GetDeclareById
import com.example.mywork122.domain.usecase.GetStatAndTargets
import com.example.mywork122.domain.usecase.GetSunday
import com.example.mywork122.domain.usecase.GetWeekId
import com.example.mywork122.domain.usecase.GetWeekSumFlow
import com.example.mywork122.domain.usecase.InsertWeekSum
import com.example.mywork122.domain.usecase.OnDeleteDeclareById
import com.example.mywork122.domain.usecase.OnLegalityCheck
import com.example.mywork122.domain.usecase.OnSubmitDeclare
import com.example.mywork122.domain.usecase.OnSubmitTargets
import com.example.mywork122.domain.usecase.ShouldUpdateWeekSum
import com.example.mywork122.domain.usecase.UpdateStatisticsObj
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //this will be the central place we define how the depnedncies are created
//and the life time will be singleton (one instance to the all App)
    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideRepository(driver: SqlDriver): Repository {
        return RepositoryImpl(WorkDatabase(driver))
    }

    @Provides
    @Singleton
    fun providEntryUseCase(repository: Repository): EntryUseCase {
        return EntryUseCase(
            GetSunday(),
            CalculateWeekSum(),
            GetDaySumByDate(repository),
            GetWeekId(),
            ShouldUpdateWeekSum(repository),
            InsertWeekSum(repository),
            UpdateStatisticsObj(repository),
            GetStatAndTargets(repository),
        )
    }

    @Provides
    @Singleton
    fun providAllUseCase(repository: Repository): AllUseCase {
        return AllUseCase(
            getSunday = GetSunday(),
            calculateWeekSum = CalculateWeekSum(),
            getDaySumByDate = GetDaySumByDate(repository),
            getWeekId = GetWeekId(),
            getStatAndTargets = GetStatAndTargets(repository),
            getWeekSumFlow = GetWeekSumFlow(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAddEditUseCase(repository: Repository): AddEditUsecase {
        return AddEditUsecase(
            onSubmitDeclare = OnSubmitDeclare(repository),
            onlegalityCheck = OnLegalityCheck(),
            getDeclareById = GetDeclareById(repository = repository),
            onDeleteDeclareById = OnDeleteDeclareById(repository)
        )
    }
}



