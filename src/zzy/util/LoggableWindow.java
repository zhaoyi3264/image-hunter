package zzy.util;

import javax.swing.JFrame;

import zzy.view.processor.AutoScrollPane;

public class LoggableWindow extends JFrame{
	private static final long serialVersionUID = -3935044412775753564L;
	protected AutoScrollPane console = new AutoScrollPane();

	public void clearConsole() {
		console.clearConsole();
	}

	public void toggleLineWrap() {
		console.toggleLineWrap();
	}
}
