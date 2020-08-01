package me.loidsemus.crowdfunding.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import me.loidsemus.crowdfunding.Crowdfunding
import me.loidsemus.crowdfunding.gui.MainMenu
import org.bukkit.entity.Player

@Suppress("unused")
@CommandAlias("crowdfunding")
class MainCommand(private val plugin: Crowdfunding) : BaseCommand() {

    @Default
    fun onDefault(player: Player) {
        MainMenu(plugin).show(player)
    }

}