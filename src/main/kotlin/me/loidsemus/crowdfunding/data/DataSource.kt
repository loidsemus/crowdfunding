package me.loidsemus.crowdfunding.data

import me.loidsemus.crowdfunding.actions.CampaignAction

abstract class DataSource {

    abstract fun saveCampaign(campaign: Campaign)
    abstract fun getActions(campaignId: Int): List<CampaignAction<*>>
    abstract fun getAllCampaigns(): List<Campaign>

}