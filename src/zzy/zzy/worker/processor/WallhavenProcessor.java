package zzy.worker.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import zzy.view.processor.AutoScrollPane;
import zzy.view.processor.ClipboardMonitor;

/**
 * A Wallhaven URL processor
 * 
 * @author Zhaoyi
 */
public class WallhavenProcessor extends Processor {
	/**
	 * Constructs a Wallhaven processor with previous records
	 * 
	 * @param records       - previous records
	 * @param failedRecords - previous failed records
	 * @param parent        - parent window of processor
	 * @param console       - console to print message to
	 */
	public WallhavenProcessor(Set<String> records, List<String> failedRecords,
			ClipboardMonitor parent, AutoScrollPane console) {
		super(records, failedRecords, parent, console);
	}

	/**
	 * Check if the URL is legal
	 * 
	 * @param url - URL to check
	 * @throws Exception if the URL is not legal
	 */
	@Override
	protected void check(String url) throws Exception {
		if (!Pattern.matches("https://wallhaven.cc/w/[a-z0-9]{6}", url))
			throw new Exception();
	}

	/**
	 * Get the resource URL base on the given URL
	 * 
	 * @param url   - base URL
	 * @param trial - number of times to try to get connection
	 * @return the resource URL
	 * @throws IOException if cannot get URL connection
	 */
	@Override
	protected String getTrueURL(String url, int trial) throws IOException {
		try {
			Document doc = Jsoup.connect(url).get(); // TODO: time out
			Element wallpaper = doc.getElementById("wallpaper");
			return wallpaper.attr("src");
		} catch (IOException e) {
			if (++trial > MAX_TRIALS)
				throw new IOException();
			return getTrueURL(url, trial);
		}
	}
}