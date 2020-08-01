package me.loidsemus.crowdfunding.actions

import org.apache.commons.lang.StringUtils
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer

class CommandAction(id: Int?, value: String) : CampaignAction<String>(id, ActionType.COMMAND, value) {

    override fun execute(creator: OfflinePlayer) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), StringUtils.replace(value, "<creator>", creator.name))
    }

    override fun valueFromString(string: String): String = value

    override fun valueToString(): String = value
}