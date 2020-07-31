package me.loidsemus.crowdfunding.config.lang

import de.themoep.minedown.MineDown
import net.md_5.bungee.api.chat.BaseComponent

class Message(val text: String) {

    /**
     * Replaces placeholders and formats with MineDown
     */
    fun get(vararg replacements: Pair<String, String>): Array<out BaseComponent> {
        return MineDown(text).replace(replacements.toMap()).toComponent()
    }

}