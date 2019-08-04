package zzy.view.processor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import zzy.dialog.MessageDialog;
import zzy.util.LoggableWindow;
import zzy.util.Utils;
import zzy.view.collector.CollectorWindow;
import zzy.worker.processor.GeneralProcessor;
import zzy.worker.processor.Mode;
import zzy.worker.processor.Processor;
import zzy.worker.processor.WallhavenProcessor;
import zzy.worker.processor.YandereProcessor;

/**
 * Main frame for the Clipboard monitor, the container for other components
 * 
 * @author Zhaoyi
 */
public class ClipboardMonitor extends LoggableWindow {
	private static final long serialVersionUID = 8497022326615912226L;
	private static final String LINE_SEPARATOR = "---------------";

	private final Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
	private Processor p;
	private CollectorWindow c;
	private String pre, content; // last and current record in the clipboard
	private boolean monitoring;

	private ModeComboBox box;

	/**
	 * COnstruct a main window of size 400 * 400
	 */
	public ClipboardMonitor() {
		this(400, 400);
	}

	/**
	 * Construct a main window of given size
	 * 
	 * @param w - width of the window
	 * @param h - height of the window
	 */
	public ClipboardMonitor(int w, int h) {
//		changeToSystemUI();

		// setup the GridBagLayout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(gb);
		gc.weightx = 1;
		gc.insets = new Insets(0, 10, 0, 10);
		gc.gridwidth = GridBagConstraints.REMAINDER;

		// mode combo box
		box = new ModeComboBox(this);
		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		Utils.addComponent(this, gb, gc, box);

		// console in the middle
		gc.weighty = 8;
		gc.fill = GridBagConstraints.BOTH;
		Utils.addComponent(this, gb, gc, console);
		
		// create Processor according to the selected item in the combo box 
		changeProcessor();

		// menu bar
		setJMenuBar(new TopMenuBar(this));

		// buttons at the button
		gc.weighty = 1;
		gc.gridwidth = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		Utils.addComponent(this, gb, gc, new ProcessorButton("Export", this));
		Utils.addComponent(this, gb, gc, new ProcessorButton("Clear", this));
		Utils.addComponent(this, gb, gc, new ProcessorButton("Exit", this));

		// initialize the window
		// setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Clipboard Monitor");
		setSize(w, h);
		Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((ss.width - w) >> 1, (ss.height - h) >> 1); // in the center of the screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// initialize the system clipboard
		setVisible(true);
		initClipboard();
		monitoring = true;
		console.log("Monitoring the system clipboard");
		c = new CollectorWindow(this);
	}

	// ----------------------Methods for Buttons and ComboBox------------------------------------//

	/**
	 * Toggle the status of the monitor between pause and resume and print message
	 */
	public void changeStatus() {
		monitoring = !monitoring;
		initClipboard();
		if (monitoring)
			console.log(LINE_SEPARATOR + "Resume" + LINE_SEPARATOR);
		else
			console.log(LINE_SEPARATOR + "  Pause " + LINE_SEPARATOR);
	}

	public void pause() {
		if (monitoring)
			changeStatus();
		else
			console.log(LINE_SEPARATOR + "  Pause " + LINE_SEPARATOR);
	}

	public void openCollector() {
		c.setLocationRelativeTo(this);
		c.setVisible(true);
	}

	/**
	 * Exit the main window, ask for confirmation if there are unexported or failed records
	 */
	public void exit() {
		monitoring = false;
		console.log(LINE_SEPARATOR + "  Pause " + LINE_SEPARATOR);
		if (p.getRecords().size() > 0 || p.getFailedRecords().size() > 0) { // ask for confirmation
			int choice = JOptionPane.showOptionDialog(this,
					"Do you want to quit?\r\nBoth records will be lost!", "WARNING",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, Processor.options,
					Processor.options[1]);
			if (choice == 0) // exit if "Yes" is clicked
				System.exit(0);
		} else { // quit if there are no records
			System.exit(0);
		}
	}

	/**
	 * Change the Processor according to the selected item in the combo box, copying
	 * the unexported and failed records
	 */
	public void changeProcessor() {
		// copy the records if the Processor is not null
		Set<String> records = p != null ? p.getRecords() : null;
		List<String> failedRecords = p != null ? p.getFailedRecords() : null;
		switch ((Mode) box.getSelectedItem()) {
			case Wallhaven:
				if (!(p instanceof WallhavenProcessor))
					p = new WallhavenProcessor(records, failedRecords, this, console);
				else
					return;
				break;
			case Yandere:
				if (!(p instanceof YandereProcessor))
					p = new YandereProcessor(records, failedRecords, this, console);
				else return;
				break;
			case General:
				if (!(p instanceof GeneralProcessor))
					p = new GeneralProcessor(records, failedRecords, this, console);
				else
					return;
				break;
		}
		console.log(box.getSelectedItem() + " processor loaded");
	}

	public Processor getProcessor() {
		return p;
	}

	// ----------------------Methods for ClipboardMonitor---------------------------------------//

	/**
	 * Initialize the system clipboard records and the record held by the monitor to
	 * empty string
	 */
	private void initClipboard() {
		cb.setContents(new StringSelection(""), null);
		content = pre = "";
	}

	/**
	 * Process the clipboard record, which contains multiple lines and show the result
	 * 
	 * @param records - the clipboard multiple-line records to be processed
	 */
	private void processMultiLine(String records) {
		console.log("Multiple records detected");
		int collected = 0; // count the non-duplicate records
		for (String rec : records.split("\n")) {
			if (p.add(rec)) {
				console.log(rec);
				collected++;
			}
		}
		console.log(collected + " URLs collected");
	}

	/**
	 * Check if the current clipboard record is different from the preivious one.
	 * Add the records to the monitor if it is not a duplicate
	 * 
	 * @throws Exception if the program cannot access the system clipboard
	 */
	private void monitor() throws Exception { // TODO: remove try-catch ?? need more tests!!!
//		try {
		if (!(content = (String) cb.getData(DataFlavor.stringFlavor)).equals(pre)) {
			pre = content;
			if (pre.contains("\n")) { // multiple lines
				processMultiLine(pre);
			} else { // single line
				if (p.add(pre))
					console.log(pre);
				else // duplicate
					new MessageDialog(this, "Error", "Duplicate URL!");
			}
		}
		Thread.sleep(50);
//		} catch (Exception e) {
//			System.err.println("Error");
//		}
	}

	/**
	 * A forever loop that monitor the system clipboard depending on the status variable
	 */
	public void start() {
		while (true) {
			try {
				Thread.sleep(50);
				while (monitoring)
					monitor();
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
	}

	/**
	 * Change to the system UI
	 */
	public void changeToSystemUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClipboardMonitor m = new ClipboardMonitor();
		m.start();
	}
}