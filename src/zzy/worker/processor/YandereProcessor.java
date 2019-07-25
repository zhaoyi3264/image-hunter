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
 * A Yandere URL processor
 * 
 * @author Zhaoyi
 */
public class YandereProcessor extends Processor {
	private String[] candidate = new String[] { "png", "highres", "highres-show" };

	/**
	 * Constructs a Yandere processor with previous records
	 * 
	 * @param records       - previous records
	 * @param failedRecords - previous failed records
	 * @param parent        - parent window of processor
	 * @param console       - console to print message to
	 */
	public YandereProcessor(Set<String> records, List<String> failedRecords,
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
		if (!Pattern.matches("https://yande.re/post/show/\\d+", url))
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
		Document document; // TODO: time out
		try {
			document = Jsoup.connect(url).get();
		} catch (Exception e) {
			return getTrueURL(url, trial);
		}
		Element sidebar = document.getElementsByClass("sidebar").get(0);
		Element image = null;
		for (int i = 0; ((image == null) && i < candidate.length); i++)
			image = sidebar.getElementById(candidate[i]);
		return image.attr("href");
	}
}
