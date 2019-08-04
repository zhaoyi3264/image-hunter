package zzy.view.processor;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A JScrollPane that contains a JTextArea and can scroll to the bottom when a
 * new message is printed
 * 
 * @author Zhaoyi
 */
public class AutoScrollPane extends JScrollPane {
	private static final long serialVersionUID = -8358633654346219502L;
	private JTextArea console;
	private JScrollBar scrollBar;

	/**
	 * Construct a auto scroll pane with non-editable JTextArea
	 */
	public AutoScrollPane() {
		console = new JTextArea();
		console.setEditable(false);
		setViewportView(console);
		this.scrollBar = getVerticalScrollBar();
	}

	/**
	 * Construct a auto scroll pane with non-editable JTextArea with a given size
	 * 
	 * @param cols - number of columns
	 * @param rows - number of rows
	 */
	public AutoScrollPane(int rows, int cols) {
		this();
		console.setRows(rows);
		console.setColumns(cols);
	}

	/**
	 * Construct the auto scroll pane with the given JTextArea
	 * 
	 * @param console - A JTextArea to show messages
	 */
	public AutoScrollPane(JTextArea console) {
		super(console);
	}

	/**
	 * Return the JTextArea of the scroll pane
	 * 
	 * @return the text area
	 */
	public JTextArea getConsole() {
		return console;
	}

	/**
	 * Print a message to the JTextArea, print a new line, and scroll to the bottom
	 * 
	 * @param message - the message to print
	 */
	public void log(String message) {
		console.append(message + "\r\n");
		scrollBar.setValue(scrollBar.getMaximum());
	}

	/**
	 * Toggle the line wrap property of the JTextArea
	 */
	public void toggleLineWrap() {
		console.setLineWrap(!console.getLineWrap());
	}

	/**
	 * Clear the content in the JTextArea
	 */
	public void clearConsole() {
		console.setText("");
	}
}
