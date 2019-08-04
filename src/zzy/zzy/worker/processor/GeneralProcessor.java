package zzy.worker.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import zzy.view.processor.AutoScrollPane;
import zzy.view.processor.ClipboardMonitor;

/**
 * A general URL processor
 * 
 * @author Zhaoyi
 */
public class GeneralProcessor extends Processor {

	/**
	 * Constructs a general processor with previous records
	 * 
	 * @param records       - previous records
	 * @param failedRecords - previous failed records
	 * @param parent        - parent window of processor
	 * @param console       - console to print message to
	 */
	public GeneralProcessor(Set<String> records, List<String> failedRecords,
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
		if (!Pattern.matches("^https://(\\S\\.)+(\\S+/)\\S+\\.\\S+.*", url.trim()))
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
		return url;
	}
}
