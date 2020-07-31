package me.loidsemus.crowdfunding

import co.aikar.commands.PaperCommandManager
import co.aikar.idb.DB
import me.loidsemus.crowdfunding.commands.MainCommand
import me.loidsemus.crowdfunding.config.MainConfig
import me.loidsemus.crowdfunding.config.lang.LanguageConfig
import me.loidsemus.crowdfunding.data.DataSource
import me.loidsemus.crowdfunding.data.SQLiteDataSource
import org.bukkit.plugin.java.JavaPlugin

class Crowdfunding : JavaPlugin() {

    val mainConfig = MainConfig(dataFolder)
    val messages = LanguageConfig(dataFolder)
    lateinit var dataSource: DataSource private set
    lateinit var campaignManager: CampaignManager private set

    override fun onEnable() {
        createFiles()
        mainConfig.loadAndSave()
        messages.loadAndSave()

        dataSource = SQLiteDataSource(this)

        campaignManager = CampaignManager()

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