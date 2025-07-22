package com.ilizma.main.view.widget.model

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.action.ActionParameters

object PlayerStateKeys {
    val state = stringPreferencesKey("state")
    val actionKey = ActionParameters.Key<String>("widgetPlayerAction")
}