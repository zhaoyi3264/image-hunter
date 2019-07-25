package zzy.dialog;

import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * A dialog that display a single line message
 * 
 * @author Zhaoyi
 */
public class MessageDialog extends CloseOnLoseFocusDialog {
	private static final long serialVersionUID = -3717156359518697681L;
	private JTextArea display;

	/**
	 * Construct a dialog
	 * 
	 * @param parent  - the main window
	 * @param title   - title of the dialog
	 * @param message - message to print
	 */
	public MessageDialog(JFrame parent, String title, String message) {
		super(parent, title);
		this.display = new JTextArea("\r\n    " + message + "    \r\n");
		display.setEditable(false);
		addQuitListenerOn(display);
		add(display);
		showDialog();
	}
	
	/**
	 * Set the message in the dialog
	 * 
	 * @param message - the message to show
	 */
	public void setText(String message) {
		display.setText("\r\n    " + message + "    \r\n");
	}
}
