package me.loidsemus.plugin

import co.aikar.commands.PaperCommandManager
import co.aikar.idb.DB
import me.loidsemus.plugin.commands.MainCommand
import me.loidsemus.plugin.config.MainConfig
import me.loidsemus.plugin.config.lang.LanguageConfig
import me.loidsemus.plugin.data.DataSource
import me.loidsemus.plugin.data.SQLiteDataSource
import org.bukkit.plugin.java.JavaPlugin

class Template : JavaPlugin() {

    val mainConfig = MainConfig(dataFolder)
    val messages = LanguageConfig(dataFolder)
    lateinit var dataSource: DataSource private set

    override fun onEnable() {
        createFiles()
        mainConfig.loadAndSave()
        messages.loadAndSave()

        dataSource = SQLiteDataSource(this)

        val commandManager = PaperCommandManager(this)
        commandManager.registerCommand(MainCommand(this))
    }

    override fun onDisable() {
        DB.close()
    }

    private fun createFiles() {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }
    }

}