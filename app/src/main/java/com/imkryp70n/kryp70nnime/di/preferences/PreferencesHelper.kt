package com.imkryp70n.kryp70nnime.di.preferences

import com.imkryp70n.kryp70nnime.model.favorite.FavoriteModel
import com.orhanobut.hawk.Hawk

class PreferencesHelper  {
    companion object {
        const val KEY_FAVORITE = "key_favorite"

    }

    fun saveFavorite(favorite : FavoriteModel) {
        Hawk.put(KEY_FAVORITE, favorite)
    }
}