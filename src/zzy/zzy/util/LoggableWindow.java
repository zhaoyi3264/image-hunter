package zzy.util;

import javax.swing.JFrame;

import zzy.view.processor.AutoScrollPane;

/**
 * A window that contains a AutoScrollPane
 * 
 * @author Zhaoyi
 *
 */
public abstract class LoggableWindow extends JFrame{
	private static final long serialVersionUID = -3935044412775753564L;
	protected AutoScrollPane console = new AutoScrollPane();

	/**
	 * Clear the console
	 */
	public void clearConsole() {
		console.clearConsole();
	}

	/**
	 * Toggle the line wrap of the console 
	 */
	public void toggleLineWrap() {
		console.toggleLineWrap();
	}
}
