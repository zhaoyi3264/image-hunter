package zzy.view.processor;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * Buttons at the bottom of the clip board monitor. The buttons provide quick access
 * to some functions
 * 
 * @author Zhaoyi
 */
public class ProcessorButton extends JButton {
	private static final long serialVersionUID = 5008630888955147427L;

	public ProcessorButton(String text, ClipboardMonitor parent) {
		super(text);
		addActionListener((ActionEvent e) -> {
			switch (e.getActionCommand()) {
				case "Export":
					parent.getProcessor().export();
					break;
				case "Clear":
					parent.getProcessor().clearRecords();
					break;
				case "Exit":
					parent.exit();
					break;
			}
		});
	}
}
