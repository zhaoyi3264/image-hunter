package zzy.worker.processor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import zzy.view.processor.AutoScrollPane;
import zzy.view.processor.ClipboardMonitor;

public class WallhavenProcessor extends Processor {

	public WallhavenProcessor(Set<String> records, List<String> failedRecords,
			ClipboardMonitor parent, AutoScrollPane console) {
		super(records, failedRecords, parent, console);
	}

	@Override
	protected void check(String url) throws Exception {
		if(!Pattern.matches("https://wallhaven.cc/w/[a-z0-9]{6}", url))
			throw new Exception();
	}

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

	public static void main(String[] args) { //TODO: remove
		String[] r = new String[] { "https://wallhaven.cc/w/111111" };
//				, "https://wallhaven.cc/w/j5vvgp" };
		HashSet<String> records = new HashSet<String>();
		for (String string : r) {
			records.add(string);
		}
	}
}