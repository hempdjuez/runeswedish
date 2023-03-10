package se.runeswedish;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("runeswedish")
public interface RuneSwedishConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Välkomst Meddelande",
		description = "Det här meddelandet visas när en användare loggar in"
	)
	default String greeting()
	{
		return "Hello";
	}
}
