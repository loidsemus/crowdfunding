package me.loidsemus.crowdfunding.actions

import org.bukkit.OfflinePlayer

abstract class Action<V>(val value: V) {

    abstract fun execute(creator: OfflinePlayer)

}