package zzy.dialog;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JDialog;

/**
 * A JDialog that can close when it loses the focus or certain key is pressed
 * 
 * @author Zhaoyi
 */
public class CloseOnLoseFocusDialog extends JDialog implements WindowFocusListener {
	private static final long serialVersionUID = -6111093734415304704L;
	private Window parent;

	/**
	 * Construct a dialog
	 * 
	 * @param parent - the main window
	 * @param title  - title of the dialog
	 */
	public CloseOnLoseFocusDialog(Window parent, String title) {
		super(parent, title);
		this.parent = parent;
		addWindowFocusListener(this);
	}

	/**
	 * Initialize the dialog and display it
	 */
	public void showDialog() {
		pack();
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	/**
	 * Empty
	 */
	@Override
	public void windowGainedFocus(WindowEvent e) {
	}

	/**
	 * Dispose the dialog when it loses the focus or certain key is pressed
	 */
	@Override
	public void windowLostFocus(WindowEvent e) {
		dispose();
	}

	/**
	 * Add a customed key listener
	 * 
	 * @param c - the component to be registered
	 */
	public void addQuitListenerOn(Component c) {
		c.addKeyListener(new KeyAdapter() {

			/**
			 * Dispose the dialog when esc, enter, or space is pressed
			 */
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_ESCAPE:
					case KeyEvent.VK_ENTER:
					case KeyEvent.VK_SPACE:
						dispose();
						break;
				}
			}
		});
	}
}
