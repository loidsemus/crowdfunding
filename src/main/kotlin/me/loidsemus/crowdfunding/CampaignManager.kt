package me.loidsemus.crowdfunding

import me.loidsemus.crowdfunding.data.Campaign

class CampaignManager {

    private val campaigns = mutableListOf<Campaign>()

    fun addCampaign(campaign: Campaign) {
        campaigns += campaign
    }

    fun removeCampaign(campaign: Campaign) {
        campaigns.remove(campaign)
    }

    fun getAllCampaigns(): List<Campaign> {
        return campaigns.toList()
    }

}