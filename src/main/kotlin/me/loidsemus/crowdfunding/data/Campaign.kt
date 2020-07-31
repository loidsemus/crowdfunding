package me.loidsemus.crowdfunding.data

import me.loidsemus.crowdfunding.actions.Action
import java.util.*

data class Campaign(
    val id: Int,
    val creator: UUID,
    val name: String,
    val description: String,
    val state: State,
    val actions: MutableList<Action<*>>
)