package zzy.dialog;

import java.awt.Cursor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import zzy.util.LoggableWindow;

public class ProgressDialog extends JDialog {
	private static final long serialVersionUID = 74379825842921138L;
	private JProgressBar bar;
	private int total;
	private int completed;
	private boolean complete;

	public ProgressDialog(LoggableWindow parent, String title) {
		super(parent);
		bar = new JProgressBar(0, 100);
		bar.setStringPainted(true);
		bar.setString("");
		bar.setIndeterminate(true);
		display(parent, title);
	}

	public ProgressDialog(LoggableWindow parent, int total, String title) {
		super(parent);
		this.complete = false;
		this.completed = 0;
		this.total = total;
		bar = new JProgressBar(0, 100);
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setString("0/" + total);
		display(parent, title);
	}

	private void display(LoggableWindow parent, String title) {
		add(bar);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!complete)
					setVisible(true);
				else
					dispose();
			}
		});
		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle(title);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setVisible(true);
	}

	public void increment() {
		int progress = (int) (++completed * 100.0 / total);
		bar.setValue(progress);
		bar.setString(completed + "/" + total);
	}

	public void setText(String s) {
		bar.setString(s);
	}
	
	public void complete(String title) {
		setTitle(title);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		complete = true;
		if(bar.isIndeterminate())
			bar.setIndeterminate(false);
	}
}
