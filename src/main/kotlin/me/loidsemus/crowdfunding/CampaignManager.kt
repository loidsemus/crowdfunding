package me.loidsemus.crowdfunding

import me.loidsemus.crowdfunding.data.Campaign

class CampaignManager {

    private val campaigns = mutableMapOf<Int, Campaign>()

    fun addCampaign(campaign: Campaign) {
        campaigns[campaign.id] = campaign
    }

    fun removeCampaign(campaign: Campaign) {
        campaigns.remove(campaign.id)
    }

    fun removeCampaign(id: Int) {
        campaigns.remove(id)
    }

    fun getAllCampaigns(): List<Campaign> {
        return campaigns.values.toList()
    }

}