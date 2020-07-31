package me.loidsemus.plugin.config.lang

import de.exlll.configlib.annotation.Comment
import de.exlll.configlib.configs.yaml.YamlConfiguration
import de.exlll.configlib.format.FieldNameFormatters
import java.io.File

@Comment(
    "Most if not all messages in the plugin are configurable here.",
    "It's important to have an ' (apostrophe, single quote) at the start and end of the message value.",
    "To use apostrophes in other places you'll have to escape them by writing them two times.",
    "The placeholders available for a certain value are listed above it, those without listed placeholders don't have any.",
    "All placeholders are specified by surrounding them with % (percent sign), e.g %placeholder%",
    "Further than normal color codes (&), this plugin supports MineDown (https://github.com/Phoenix616/MineDown#syntax),",
    "which allows for advanced messages with hover events and much more.",
    "You can even do things like use placeholders inside hover messages if you'd like."
)
class LanguageConfig(dir: File) : YamlConfiguration(
    File(dir, "messages.yml").toPath(),
    YamlProperties.builder().addDefaultConverter(Message::class.java, MessageConverter())
        .setFormatter(FieldNameFormatters.LOWER_UNDERSCORE).build()
)