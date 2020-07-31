package me.loidsemus.crowdfunding.data

import co.aikar.idb.DB
import co.aikar.idb.Database
import co.aikar.idb.DatabaseOptions
import co.aikar.idb.PooledDatabaseOptions
import me.loidsemus.crowdfunding.Crowdfunding
import java.io.File

class SQLiteDataSource(plugin: Crowdfunding) : DataSource(plugin) {

    private val database: Database

    init {
        val dbOptions = DatabaseOptions.builder()
            .sqlite(File(plugin.dataFolder, "data.db").path)
            .build()
        database = PooledDatabaseOptions.builder().options(dbOptions).createHikariDatabase()
        DB.setGlobalDatabase(database)

        //language=SQLite
        database.executeUpdate(
            """
            create table if not exists crowdfunding_campaigns (
            id integer primary key autoincrement,
            creator varchar(36),
            name text,
            description text,
            state text
            );
        """.trimIndent()
        )
        //language=SQLite
        database.executeUpdate(
            """
                create table if not exists crowdfunding_actions (
                id integer primary key autoincrement,
                campaign_id integer not null,
                type text not null,
                value text,
                foreign key (campaign_id) references crowdfunding_campaigns(id)
                );
            """.trimIndent()
        )
    }

}