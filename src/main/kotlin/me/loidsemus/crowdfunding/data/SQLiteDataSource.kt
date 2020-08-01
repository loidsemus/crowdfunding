package me.loidsemus.crowdfunding.data

import co.aikar.idb.DB
import co.aikar.idb.Database
import co.aikar.idb.DatabaseOptions
import co.aikar.idb.PooledDatabaseOptions
import me.loidsemus.crowdfunding.Crowdfunding
import me.loidsemus.crowdfunding.actions.ActionType
import me.loidsemus.crowdfunding.actions.CampaignAction
import java.io.File

class SQLiteDataSource(plugin: Crowdfunding) : DataSource() {

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
            raised real,
            goal real,
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

    override fun getActions(campaignId: Int): List<CampaignAction<*>> {
        //language=SQLite
        val actions =
            database.getResults("select id, type, value from crowdfunding_actions where campaign_id = ?;", campaignId)
        val result = mutableListOf<CampaignAction<*>>()
        for (action in actions) {
            val type = ActionType.valueOf(action.getString("type"))
            result.add(type.toAction(action.getInt("id"), action.getString("value")))
        }
        return result
    }

    override fun getAllCampaigns(): List<Campaign> {
        //language=SQLite
        val campaigns = database.getResults("select * from crowdfunding_campaigns;")
        val result = mutableListOf<Campaign>()
        for (campaign in campaigns) {
            result.add(Campaign.of(campaign, getActions(campaign.getInt("id"))))
        }
        return result
    }

    override fun saveCampaign(campaign: Campaign) {
        //language=SQLite
        val insertedCampaignId = database.executeInsert(
            """insert into crowdfunding_campaigns (id, creator, name, description, raised, goal, state) values (?, ?, ?, ?, ?, ?, ?)
            on conflict(id) do update set name = ?, description = ?, raised = ?, goal = ?, state = ?;""".trimMargin(),
            campaign.id, campaign.creator, campaign.name, campaign.description, campaign.raised, campaign.goal, campaign.state.name,
            campaign.name, campaign.description, campaign.raised, campaign.goal, campaign.state.name
        )
        for (action in campaign.actions) {
            //language=SQLite
            val insertedActionId = database.executeInsert(
                """insert into crowdfunding_actions (id, campaign_id, type, value) values (?, ?, ?, ?)
                on conflict(id) do update set value = ?;""".trimMargin(),
                action.id, insertedCampaignId.toInt(), action.type, action.valueToString(),
                action.valueToString()
            )
            if (action.id == null) {
                action.id = insertedActionId.toInt()
            }
        }
        if (campaign.id == null) {
            campaign.id = insertedCampaignId.toInt()
        }
    }
}