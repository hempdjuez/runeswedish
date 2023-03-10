package se.runeswedish;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;

@Slf4j
@PluginDescriptor(
	name = "Rune Swedish"
)
public class RuneSwedish extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RuneSwedishConfig config;

	private HashMap<String,String> seTranslation;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Rune Swedish started!");
		seTranslation = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/se.csv")))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String str[] = line.split(",");
				seTranslation.put(str[0], str[1]);
			}
		} catch (Exception e){
			log.error(e.getMessage());
		}
	}
	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event)
	{
		try {
			String eng = event.getMenuEntry().getOption();
			if (seTranslation.containsKey(eng)) {
				String tran = seTranslation.get(eng);
				event.getMenuEntry().setOption(event.getOption().replace(eng, tran));
			}
		} catch (Exception e){
			log.error(e.getMessage());
		}
	}
	@Override
	protected void shutDown() throws Exception
	{
		log.info("RuneSwedish stopped!");
	}
	@Provides
	RuneSwedishConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RuneSwedishConfig.class);
	}
}
