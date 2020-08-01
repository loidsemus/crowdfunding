package me.loidsemus.crowdfunding.gui

import de.themoep.inventorygui.*
import me.loidsemus.crowdfunding.Crowdfunding
import me.loidsemus.crowdfunding.actions.CampaignAction
import me.loidsemus.crowdfunding.actions.CommandAction
import me.loidsemus.crowdfunding.data.Campaign
import me.loidsemus.crowdfunding.data.State
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MainMenu(plugin: Crowdfunding) : InventoryGui(plugin, plugin.messages.mainMenuHeader.getColoredOnly(), GUI_ROWS) {

    companion object {
        val GUI_ROWS = arrayOf(
            "ggggggggg",
            "ggggggggg",
            "ggggggggg",
            "ggggggggg",
            "ggggggggg",
            "   pcn   "
        )
    }

    init {
        val group = GuiElementGroup('g')
        for (it in plugin.campaignManager.getAllCampaigns()) {
            val itemStack = ItemStack(
                when (it.state) {
                    State.ACTIVE -> Material.DIAMOND
                    State.COMPLETED -> Material.EMERALD
                    State.CANCELED -> Material.REDSTONE
                }
            )

            group.addElement(StaticGuiElement('e', itemStack, GuiElement.Action { click ->
                it.actions.forEach {
                    it.execute(click.event.whoClicked as Player)
                }
                true
            }, it.name))
        }
        addElement(group)

        addElement(StaticGuiElement('c', ItemStack(Material.EMERALD), GuiElement.Action {
            // TODO: Create new campaign
            val actions = listOf<CampaignAction<*>>(
                CommandAction(null, "say hello")
            )
            val campaign = Campaign(
                null,
                it.event.whoClicked.uniqueId,
                "test name",
                "test desc",
                State.ACTIVE,
                actions.toMutableList()
            )
            plugin.dataSource.saveCampaign(campaign)
            plugin.campaignManager.addCampaign(campaign)
            true
        }, plugin.messages.newCampaign.getColoredOnly()))

        addElement(GuiPageElement('p', ItemStack(Material.ARROW), GuiPageElement.PageAction.PREVIOUS, "Prev. page"))
        addElement(GuiPageElement('n', ItemStack(Material.ARROW), GuiPageElement.PageAction.NEXT, "Next page"))
    }

}