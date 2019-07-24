package zzy.worker.processor;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import zzy.view.processor.AutoScrollPane;
import zzy.view.processor.ClipboardMonitor;

public class GeneralProcessor extends Processor {

	public GeneralProcessor(Set<String> records, List<String> failedRecords,
			ClipboardMonitor parent, AutoScrollPane console) {
		super(records, failedRecords, parent, console);
	}

	@Override
	protected void check(String url) throws Exception{
		if(!Pattern.matches("^https://(\\S\\.)+(\\S+/)\\S+\\.\\S+.*", url.trim()))
			throw new Exception();
	}

	@Override
	protected String getTrueURL(String url, int trial) throws IOException {
		return url;
	}
}
