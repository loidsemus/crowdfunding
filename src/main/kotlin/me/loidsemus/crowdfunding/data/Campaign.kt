package me.loidsemus.crowdfunding.data

import co.aikar.idb.DbRow
import me.loidsemus.crowdfunding.actions.CampaignAction
import java.util.*

data class Campaign(
    var id: Int?,
    val creator: UUID,
    var name: String,
    var description: String,
    var state: State,
    val actions: MutableList<CampaignAction<*>>
) {

    companion object {
        fun of(row: DbRow, actions: List<CampaignAction<*>>): Campaign {
            return Campaign(
                row.getInt("id"),
                UUID.fromString(row.getString("creator")),
                row.getString("name"),
                row.getString("description"),
                State.valueOf(row.getString("state")),
                actions.toMutableList()
            )
        }
    }

}