package dev.mrmarshall.oozerpg.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LevelingData {

    private File mobsFile;
    private File levelsFile;

    public LevelingData() {
        mobsFile = new File("plugins/OozeRPG/Leveling/Mobs.yml");
        levelsFile = new File("plugins/OozeRPG/Leveling/Levels.yml");
    }

    public void createFiles() {
        File levelingDir = new File("plugins/OozeRPG/Leveling");

        if (!levelingDir.exists()) {
            levelingDir.mkdir();
        }

        FileConfiguration mobsFileCfg = YamlConfiguration.loadConfiguration(mobsFile);

        if (!mobsFile.exists()) {
            try {
                mobsFile.createNewFile();
                mobsFileCfg.set("animals.Chicken", 1);
                mobsFileCfg.set("animals.Cow", 2);
                mobsFileCfg.set("animals.Cod", 1);
                mobsFileCfg.set("animals.Horse", 3);
                mobsFileCfg.set("animals.Mooshroom", 3);
                mobsFileCfg.set("animals.Llama", 3);
                mobsFileCfg.set("animals.Ocelot", 2);
                mobsFileCfg.set("animals.Cat", 3);
                mobsFileCfg.set("animals.Fox", 1);
                mobsFileCfg.set("animals.Parrot", 2);
                mobsFileCfg.set("animals.Pig", 1);
                mobsFileCfg.set("animals.Sheep", 2);
                mobsFileCfg.set("animals.Squid", 2);
                mobsFileCfg.set("animals.Rabbit", 3);
                mobsFileCfg.set("animals.Wolf", 2);
                mobsFileCfg.set("animals.Polar Bear", 2);
                mobsFileCfg.set("animals.Turtle", 3);
                mobsFileCfg.set("animals.Panda", 4);
                mobsFileCfg.set("animals.Bat", 0);
                mobsFileCfg.set("animals.Dolphin", 0);

                mobsFileCfg.set("golems.Iron Golem", 0);
                mobsFileCfg.set("golems.Snow Golem", 0);

                mobsFileCfg.set("monsters.Cave Spider", 5);
                mobsFileCfg.set("monsters.Creeper", 5);
                mobsFileCfg.set("monsters.Drowned", 5);
                mobsFileCfg.set("monsters.Enderman", 5);
                mobsFileCfg.set("monsters.Evoker", 5);
                mobsFileCfg.set("monsters.Ghast", 5);
                mobsFileCfg.set("monsters.Husk", 5);
                mobsFileCfg.set("monsters.Illusioner", 5);
                mobsFileCfg.set("monsters.Phantom", 5);
                mobsFileCfg.set("monsters.Pillager", 5);
                mobsFileCfg.set("monsters.Shulker", 5);
                mobsFileCfg.set("monsters.Silverfish", 5);
                mobsFileCfg.set("monsters.Skeleton", 5);
                mobsFileCfg.set("monsters.Spider", 5);
                mobsFileCfg.set("monsters.Stray", 5);
                mobsFileCfg.set("monsters.Vindicator", 5);
                mobsFileCfg.set("monsters.Witch", 5);
                mobsFileCfg.set("monsters.Wither Skeleton", 5);
                mobsFileCfg.set("monsters.Zombie", 5);
                mobsFileCfg.set("monsters.Zombie Pigman", 5);
                mobsFileCfg.set("monsters.Vex", 5);
                mobsFileCfg.set("monsters.Endermite", 3);
                mobsFileCfg.set("monsters.Slime", 3);
                mobsFileCfg.set("monsters.Magma Cube", 3);
                mobsFileCfg.set("monsters.Blaze", 10);
                mobsFileCfg.set("monsters.Guardian", 10);
                mobsFileCfg.set("monsters.Elder Guardian", 10);
                mobsFileCfg.set("monsters.Ravager", 20);

                mobsFileCfg.set("bosses.Ender Dragon", 12000);
                mobsFileCfg.set("bosses.Wither", 50);
                mobsFileCfg.save(mobsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration levelsFileCfg = YamlConfiguration.loadConfiguration(levelsFile);

        if (!levelsFile.exists()) {
            try {
                levelsFile.createNewFile();
                levelsFileCfg.set("2", 50);
                levelsFileCfg.set("3", 50);
                levelsFileCfg.set("4", 50);
                levelsFileCfg.set("5", 50);
                levelsFileCfg.set("6", 50);
                levelsFileCfg.set("7", 50);
                levelsFileCfg.set("8", 50);
                levelsFileCfg.set("9", 50);
                levelsFileCfg.set("10", 50);
                levelsFileCfg.set("11", 50);
                levelsFileCfg.set("12", 50);
                levelsFileCfg.set("13", 50);
                levelsFileCfg.set("14", 50);
                levelsFileCfg.set("15", 50);
                levelsFileCfg.set("16", 50);
                levelsFileCfg.set("17", 50);
                levelsFileCfg.set("18", 50);
                levelsFileCfg.set("19", 50);
                levelsFileCfg.set("20", 50);
                levelsFileCfg.set("21", 50);
                levelsFileCfg.set("22", 50);
                levelsFileCfg.set("23", 50);
                levelsFileCfg.set("24", 50);
                levelsFileCfg.set("25", 50);
                levelsFileCfg.set("26", 50);
                levelsFileCfg.set("27", 50);
                levelsFileCfg.set("28", 50);
                levelsFileCfg.set("29", 50);
                levelsFileCfg.set("30", 50);
                levelsFileCfg.set("31", 50);
                levelsFileCfg.set("32", 50);
                levelsFileCfg.set("33", 50);
                levelsFileCfg.set("34", 50);
                levelsFileCfg.set("35", 50);
                levelsFileCfg.set("36", 50);
                levelsFileCfg.set("37", 50);
                levelsFileCfg.set("38", 50);
                levelsFileCfg.set("39", 50);
                levelsFileCfg.set("40", 50);
                levelsFileCfg.set("41", 50);
                levelsFileCfg.set("42", 50);
                levelsFileCfg.set("43", 50);
                levelsFileCfg.set("44", 50);
                levelsFileCfg.set("45", 50);
                levelsFileCfg.set("46", 50);
                levelsFileCfg.set("47", 50);
                levelsFileCfg.set("48", 50);
                levelsFileCfg.set("49", 50);
                levelsFileCfg.set("50", 50);
                levelsFileCfg.save(levelsFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getMobsFile() {
        return mobsFile;
    }

    public File getLevelsFile() {
        return levelsFile;
    }
}