package me.loidsemus.plugin.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import me.loidsemus.plugin.Template

@CommandAlias("template")
class MainCommand(val plugin: Template) : BaseCommand()