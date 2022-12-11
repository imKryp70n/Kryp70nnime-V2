package com.imkryp70n.kryp70nnime.view.fragments.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.imkryp70n.kryp70nnime.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}