package ywxt.tools.setting

import android.os.Bundle
import ywxt.tools.common.fragments.BasePreferenceFragment


class SettingFragment : BasePreferenceFragment() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        this.addPreferencesFromResource(R.xml.tools_setting_xml_preference) 
    }
}
