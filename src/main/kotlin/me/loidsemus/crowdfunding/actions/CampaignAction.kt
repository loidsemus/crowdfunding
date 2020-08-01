package me.loidsemus.crowdfunding.actions

import org.bukkit.OfflinePlayer

abstract class CampaignAction<V>(var id: Int?, val type: ActionType, val value: V) {

    abstract fun execute(creator: OfflinePlayer)

    abstract fun valueFromString(string: String): V
    abstract fun valueToString(): String

}