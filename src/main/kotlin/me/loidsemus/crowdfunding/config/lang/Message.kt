package me.loidsemus.crowdfunding.config.lang

import de.themoep.minedown.MineDown
import net.md_5.bungee.api.chat.BaseComponent
import org.apache.commons.lang.StringUtils
import org.bukkit.ChatColor

class Message(val text: String) {

    /**
     * Replaces placeholders and formats with MineDown
     */
    fun get(vararg replacements: Pair<String, String>): Array<out BaseComponent> {
        return MineDown(text).replace(replacements.toMap()).toComponent()
    }

    fun getColoredOnly(vararg replacements: Pair<String, String>): String {
        val components = MineDown(text).replace(replacements.toMap()).toComponent()
        val text = StringBuilder()
        components.forEach {
            text.append(it.toLegacyText())
        }
        return text.toString()
    }

}