package me.loidsemus.crowdfunding

import co.aikar.commands.PaperCommandManager
import co.aikar.idb.DB
import me.loidsemus.crowdfunding.commands.MainCommand
import me.loidsemus.crowdfunding.config.MainConfig
import me.loidsemus.crowdfunding.config.lang.LanguageConfig
import me.loidsemus.crowdfunding.data.DataSource
import me.loidsemus.crowdfunding.data.SQLiteDataSource
import org.bukkit.plugin.java.JavaPlugin
import java.math.BigDecimal

class Crowdfunding : JavaPlugin() {

    val mainConfig = MainConfig(dataFolder)
    val messages = LanguageConfig(dataFolder)
    lateinit var dataSource: DataSource private set
    lateinit var campaignManager: CampaignManager private set

    override fun onEnable() {
        val startTime = System.currentTimeMillis()
        createFiles()
        mainConfig.loadAndSave()
        messages.loadAndSave()

        dataSource = SQLiteDataSource(this)
        campaignManager = CampaignManager()
        logger.info("Loading campaigns...")
        for (campaign in dataSource.getAllCampaigns()) {
            campaignManager.addCampaign(campaign)
        }

        val commandManager = PaperCommandManager(this)
        commandManager.registerCommand(MainCommand(this))
        val totalTime = System.currentTimeMillis() - startTime
        logger.info("Plugin startup took ${totalTime}ms (${BigDecimal(totalTime).divide(BigDecimal(1000))}s)")
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