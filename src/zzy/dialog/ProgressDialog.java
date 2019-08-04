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

	public ProgressDialog(LoggableWindow parent, int total) {
		super(parent);
		this.complete = false;
		this.completed = 0;
		this.total = total;
		bar = new JProgressBar(0, 100);
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setString("0/" + total);
		add(bar);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				if (!complete)
					setVisible(true);
				else
					dispose();
			}
		});
		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setTitle("Processing...");
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setVisible(true);
	}

	public void increment() {
		int progress = (int) (++completed * 100.0 / total);
		bar.setValue(progress);
		bar.setString(completed + "/" + total);
	}

	public void complete() {
		setTitle("Process complete");
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		complete = true;
	}
}
