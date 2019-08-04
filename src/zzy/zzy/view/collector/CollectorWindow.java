package zzy.view.collector;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;

import zzy.util.LoggableWindow;
import zzy.util.Utils;
import zzy.view.processor.ClipboardMonitor;
import zzy.worker.collector.Collector;
import zzy.worker.processor.Processor;

/**
 * The main window for collector
 * 
 * @author Zhaoyi
 */
public class CollectorWindow extends LoggableWindow {
	private static final long serialVersionUID = -4730389141565011446L;
	private final ClipboardMonitor parent;
	private Collector c;
	private InputPanel input;

	/**
	 * Construct a collector window using gridbag layout
	 * 
	 * @param parent - the parent of the collector window
	 */
	public CollectorWindow(ClipboardMonitor parent) {
		this.parent = parent;

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(gb);
		gc.weightx = 1;
		gc.gridheight = 1;
		gc.insets = new Insets(3, 10, 3, 10);
		gc.gridwidth = GridBagConstraints.REMAINDER;

		// console
		gc.weighty = 10;
		gc.fill = GridBagConstraints.BOTH;
		Utils.addComponent(this, gb, gc, console);

		// input panel
		gc.weighty = 5;
		gc.fill = GridBagConstraints.BOTH;
		Utils.addComponent(this, gb, gc, input = new InputPanel());

		// buttons
		gc.weighty = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		Utils.addComponent(this, gb, gc, new CollectorButton("Collect", this));
		Utils.addComponent(this, gb, gc, new CollectorButton("Submit", this));

		// collector and menu bar
		c = new Collector(this, console);
		setJMenuBar(new TopMenuBar(this));

		setSize(400, 400);
		setTitle("Collector");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}

	/**
	 * Get the collector
	 * 
	 * @return collector
	 */
	public Collector getCollector() {
		return c;
	}

	/**
	 * Get the processor
	 * 
	 * @return processor
	 */
	public Processor getProcessor() {
		return parent.getProcessor();
	}

	/**
	 * Update the collector with the input in the input panel
	 * 
	 * @throws MalformedURLException if the error occurs among the input
	 */
	public void updateCollector() throws MalformedURLException {
		input.updateCollector(c);
	}
}
