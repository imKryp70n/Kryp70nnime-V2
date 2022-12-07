package com.imkryp70n.kryp70nnime.di

import com.imkryp70n.kryp70nnime.data.ApiClient
import com.imkryp70n.kryp70nnime.data.discovery.DiscoveryRDS
import com.imkryp70n.kryp70nnime.data.trending.TrendingRDS
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryDataSource
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryRepository
import com.imkryp70n.kryp70nnime.model.trending.TrendingDataSource
import com.imkryp70n.kryp70nnime.model.trending.TrendingRepository
import com.imkryp70n.kryp70nnime.viewmodel.Factory.DiscoverViewModelFactory
import com.imkryp70n.kryp70nnime.viewmodel.Factory.TrendingViewModelFactory

/**
 * @author Eduardo Medina
 */
object Injection {


    // DISCOVERY MODULE
    private var discoveryDataSource: DiscoveryDataSource? = null
    private var discoveryRepository: DiscoveryRepository? = null
    private var discoveryViewModelFactory: DiscoverViewModelFactory? = null

    // TRENDING MODULE
    private var trendingDataSource: TrendingDataSource? = null
    private var trendingRepository: TrendingRepository? = null
    private var trendingViewModelFactory: TrendingViewModelFactory? = null


    // Injection for Discovery Module
    private fun createDiscoveryDataSource(): DiscoveryDataSource {
        val dataSource = DiscoveryRDS(ApiClient)
        discoveryDataSource = dataSource
        return dataSource
    }

    private fun createDiscoveryRepository(): DiscoveryRepository {
        val repository = DiscoveryRepository(provideMainDataSource())
        discoveryRepository = repository
        return repository
    }


    private fun createDiscoveryFactory(): DiscoverViewModelFactory {
        val factory = DiscoverViewModelFactory(providerMainRepository(), providerTrendingRepository())
        discoveryViewModelFactory = factory
        return factory
    }



    // Injection for Trending Module
    private fun createTrendingDataSource(): TrendingDataSource {
        val dataSource = TrendingRDS(ApiClient)
        trendingDataSource = dataSource
        return dataSource
    }

    private fun createTrendingRepository(): TrendingRepository {
        val repository = TrendingRepository(provideTrendingDataSource())
        trendingRepository = repository
        return repository
    }

    private fun createTrendingFactory(): TrendingViewModelFactory {
        val factory = TrendingViewModelFactory(providerTrendingRepository())
        trendingViewModelFactory = factory
        return factory
    }



    // Main Provider
    private fun provideMainDataSource() = discoveryDataSource ?: createDiscoveryDataSource()
    private fun providerMainRepository() = discoveryRepository ?: createDiscoveryRepository()

    // Inject In Activity / Fragment
    fun provideMainViewModelFactory() = discoveryViewModelFactory ?: createDiscoveryFactory()


    // Trending Provider
    private fun provideTrendingDataSource() = trendingDataSource ?: createTrendingDataSource()
    private fun providerTrendingRepository() = trendingRepository ?: createTrendingRepository()

    // Inject In Activity / Fragment
    fun provideTrendingViewModelFactory() = trendingViewModelFactory ?: createTrendingFactory()


    fun destroy() {
        // Discovery Module
        discoveryDataSource = null
        discoveryRepository = null
        discoveryDataSource = null


        // Trending Module
        trendingDataSource = null
        trendingRepository = null
        trendingViewModelFactory = null
    }
}