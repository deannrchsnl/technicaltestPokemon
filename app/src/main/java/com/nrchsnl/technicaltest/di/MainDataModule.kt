package com.nrchsnl.technicaltest.di

/**
 * Created by deannrchsnl on 25/10/21.
 */
import android.content.Context
import com.nrchsnl.technicaltest.MainUseCase
import com.nrchsnl.technicaltest.base.BaseModule
import com.nrchsnl.technicaltest.data.MainRepository
import com.nrchsnl.technicaltest.data.MainRepositoryImp
import com.nrchsnl.technicaltest.data.ServiceFactory
import com.nrchsnl.technicaltest.data.remote.MainRemoteDataSource
import com.nrchsnl.technicaltest.data.remote.MainService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module(includes = [BaseModule::class])
class MainDataModule(private val mContext: Context) {
    @MainScope
    @Provides
    fun provideContext(): Context {
        return mContext
    }

    @MainScope
    @Provides
    fun provideService(): MainService {
        return ServiceFactory.createService(mContext, MainService::class.java)
    }

    @MainScope
    @Provides
    fun provideRepository(
        @MainScope remoteDataSource: MainRemoteDataSource
    ): MainRepository {
        return MainRepositoryImp(remoteDataSource)
    }

    @MainScope
    @Provides
    fun provideRemote(@MainScope service: MainService): MainRemoteDataSource {
        return MainRemoteDataSource(service)
    }

    @MainScope
    @Provides
    fun provideUseCase(@MainScope repository: MainRepository): MainUseCase {
        return MainUseCase(repository)
    }

    @MainScope
    @Provides
    fun provideSchedulerProvider() = Dispatchers.Main
}