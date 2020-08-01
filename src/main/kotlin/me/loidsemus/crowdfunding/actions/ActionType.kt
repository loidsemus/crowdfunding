package me.loidsemus.crowdfunding.actions

enum class ActionType {
    COMMAND {
        override fun toAction(id: Int, value: String): CampaignAction<*> {
            return CommandAction(id, value)
        }
    };

    abstract fun toAction(id: Int, value: String): CampaignAction<*>
}
