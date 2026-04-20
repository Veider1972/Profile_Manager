package ru.veider.profilemanager.repo

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import ru.veider.profilemanager.data.defaultProfiles
import ru.veider.profilemanager.domain.Profile
import ru.veider.profilemanager.domain.ProfileType
import ru.veider.profilemanager.domain.Widget
import ru.veider.profilemanager.domain.toProfileType
import ru.veider.profilemanager.utils.stateFlow

class PreferenceImpl(
    context: Context,
    gson: Gson
) : Preference, BasePreference(context, gson) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var mutex = Mutex()
    private fun CoroutineScope.withMutex(content: () -> Unit) =
        scope.launch {
            mutex.withLock {
                content()
            }
        }

    override val widget = stateFlow(
        initialValue = getObject<Widget>("widget") ?: Widget(),
        onValueChange = {
            scope.withMutex { putObject("widget", it) }
        }
    )

    override val profiles = stateFlow(
        initialValue = getList<Profile>("profiles") ?: defaultProfiles(context),
        onValueChange = {
            scope.withMutex { putList("profiles", it) }
        }
    )

    override val selectedProfilesType = stateFlow(
        initialValue = getList<ProfileType>("selectedProfiles") ?: listOf(ProfileType.Day, ProfileType.Night),
        onValueChange = {
            scope.withMutex { putList("selectedProfiles", it) }
        }
    )

    override val currentProfileType = stateFlow(
        initialValue = getInt("currentProfile")?.toProfileType() ?: ProfileType.Day,
        onValueChange = {
            scope.withMutex { putInt("currentProfile", it.value) }
        }
    )

    override fun getColor(res: Int): Color = Color(context.getColor(res))
    override fun getString(res: Int): String = context.getString(res)
}