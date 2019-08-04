package zzy.view.collector;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * A button that provides fast access of collector funtions
 * 
 * @author Zhaoyi
 */
public class CollectorButton extends JButton {
	private static final long serialVersionUID = 3606943080352986187L;

	/**
	 * Construct a button
	 * 
	 * @param text   - the button text
	 * @param parent - the parent of the button
	 */
	public CollectorButton(String text, CollectorWindow parent) {
		super(text);
		addActionListener((ActionEvent e) -> {
			switch (e.getActionCommand()) {
				case "Collect":
					parent.getCollector().collect();
					break;
				case "Submit":
					parent.getCollector().submit(parent.getProcessor());
					break;
			}
		});
	}
}
