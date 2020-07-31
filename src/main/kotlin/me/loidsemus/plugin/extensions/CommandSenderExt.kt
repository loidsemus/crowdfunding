package me.loidsemus.plugin.extensions

import me.loidsemus.plugin.config.lang.Message
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.command.CommandSender

fun CommandSender.sendComponent(vararg components: BaseComponent) {
    this.spigot().sendMessage(*components)
}

fun CommandSender.sendMessage(message: Message, vararg replacements: Pair<String, String>) {
    this.sendComponent(*message.get(*replacements))
}