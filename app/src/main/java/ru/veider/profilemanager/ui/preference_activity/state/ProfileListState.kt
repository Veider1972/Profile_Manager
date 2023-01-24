package ru.veider.profilemanager.ui.preference_activity.state

data class ProfileListState(
    val data: MutableList<ProfileState> = mutableListOf()
) {
    fun pos(id:Long):Int{
        for(i in data.indices)
            if (data[i].id==id)
                return i
        return -1
    }
}