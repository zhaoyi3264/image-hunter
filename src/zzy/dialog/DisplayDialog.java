package zzy.dialog;

import javax.swing.JFrame;

import zzy.view.processor.AutoScrollPane;

/**
 * A dialog to display a multi-line message using AutocrollPane
 * 
 * @author Zhaoyi
 */
public class DisplayDialog extends CloseOnLoseFocusDialog {
	private static final long serialVersionUID = -5604845707137995162L;
	private AutoScrollPane display;

	/**
	 * Construct a dialog
	 * 
	 * @param parent - main window
	 * @param title  - title of the dialog
	 */
	public DisplayDialog(JFrame parent, String title) {
		super(parent, title);
		display = new AutoScrollPane(15, 30);
		add(display);
		addQuitListenerOn(display.getConsole());
		showDialog();
	}

	/**
	 * Print a message to the dialog
	 * 
	 * @param message - the message to print
	 */
	public void log(String message) {
		display.log(message);
	}
}
