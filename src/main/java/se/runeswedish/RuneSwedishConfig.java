package se.runeswedish;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("runeswedish")
public interface RuneSwedishConfig extends Config
{
	@ConfigItem(
		keyName = "",
		name = "",
		description = ""
	)
	default String greeting()
	{
		return "";
	}
}
