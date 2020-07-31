package me.loidsemus.crowdfunding.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import me.loidsemus.crowdfunding.Crowdfunding

@CommandAlias("template")
class MainCommand(val plugin: Crowdfunding) : BaseCommand()