package zzy.worker.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import zzy.util.Timer;
import zzy.view.processor.AutoScrollPane;
import zzy.view.processor.ClipboardMonitor;

public class YandereProcessor extends Processor {
	private String[] candidate = new String[] { "png", "highres", "highres-show" };

	public YandereProcessor(Set<String> records, List<String> failedRecords,
			ClipboardMonitor parent, AutoScrollPane console) {
		super(records, failedRecords, parent, console);
	}

	@Override
	protected void check(String url) throws Exception {
		if(!Pattern.matches("https://yande.re/post/show/\\d+", url))
			throw new Exception();
	}

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

	public static void main(String[] args) { //TODO: remove
		try {
			Timer.start();
			YandereProcessor p = new YandereProcessor(null, null, null, null);
			String s = p.getTrueURL("https://yande.re/post/show/551939", 1);
			Timer.stop();
			System.out.println(Timer.returnStringTime());
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
