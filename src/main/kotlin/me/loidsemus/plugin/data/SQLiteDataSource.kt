package me.loidsemus.plugin.data

import co.aikar.idb.DB
import co.aikar.idb.Database
import co.aikar.idb.DatabaseOptions
import co.aikar.idb.PooledDatabaseOptions
import me.loidsemus.plugin.Template
import java.io.File

class SQLiteDataSource(plugin: Template) : DataSource(plugin) {

    private val database: Database

    init {
        val dbOptions = DatabaseOptions.builder()
            .sqlite(File(plugin.dataFolder, "data.db").path)
            .build()
        database = PooledDatabaseOptions.builder().options(dbOptions).createHikariDatabase()
        DB.setGlobalDatabase(database)
    }

}