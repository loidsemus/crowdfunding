package me.loidsemus.crowdfunding.config

import de.exlll.configlib.configs.yaml.YamlConfiguration
import java.io.File

class MainConfig(dir: File) : YamlConfiguration(File(dir, "config.yml").toPath(), YamlProperties.DEFAULT)