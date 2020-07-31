package me.loidsemus.crowdfunding.actions

import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

class CommandAction(value: String) : Action<String>(value) {

    override fun execute(creator: OfflinePlayer) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StringUtils.replace(value, "<creator>", creator.name))
    }

}