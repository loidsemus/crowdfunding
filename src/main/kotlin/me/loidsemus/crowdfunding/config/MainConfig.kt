package me.loidsemus.crowdfunding.config

import de.exlll.configlib.annotation.ConfigurationElement
import de.exlll.configlib.annotation.ElementType
import de.exlll.configlib.configs.yaml.YamlConfiguration
import java.io.File

class MainConfig(dir: File) : YamlConfiguration(File(dir, "config.yml").toPath(), YamlProperties.DEFAULT) {

    var actions = Actions()

}

@ConfigurationElement
class Actions(
    @ElementType(Command::class)
    var commands: MutableList<Command> = mutableListOf(
        Command(),
        Command("time set night", "Sets the time to night for all players", 2500.0)
    )
)

@ConfigurationElement
class Command(
    var command: String = "time set day",
    var description: String = "Sets the time to day for all players",
    var minimumCost: Double = 5000.0
)