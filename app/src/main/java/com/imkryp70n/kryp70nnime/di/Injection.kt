package com.imkryp70n.kryp70nnime.di

import com.imkryp70n.kryp70nnime.data.ApiClient
import com.imkryp70n.kryp70nnime.data.discovery.DiscoveryRDS
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryDataSource
import com.imkryp70n.kryp70nnime.model.discovery.DiscoveryRepository
import com.imkryp70n.kryp70nnime.viewmodel.discover.DiscoverViewModelFactory

/**
 * @author Eduardo Medina
 */
object Injection {


    // MAIN MODULE
    private var discoveryDataSource: DiscoveryDataSource? = null
    private var discoveryRepository: DiscoveryRepository? = null
    private var mainViewModelFactory: DiscoverViewModelFactory? = null



    // Main
    private fun createMainDataSource(): DiscoveryDataSource {
        val dataSource = DiscoveryRDS(ApiClient)
        discoveryDataSource = dataSource
        return dataSource
    }

    private fun createMainRepository(): DiscoveryRepository {
        val repository = DiscoveryRepository(provideMainDataSource())
        discoveryRepository = repository
        return repository
    }


    private fun createMainFactory(): DiscoverViewModelFactory {
        val factory = DiscoverViewModelFactory(providerMainRepository())
        mainViewModelFactory = factory
        return factory
    }


    // Main Provider
    private fun provideMainDataSource() = discoveryDataSource ?: createMainDataSource()
    private fun providerMainRepository() = discoveryRepository ?: createMainRepository()
    fun provideMainViewModelFactory() = mainViewModelFactory ?: createMainFactory()


    fun destroy() {
        discoveryDataSource = null
        discoveryRepository = null
        mainViewModelFactory = null
    }
}