package zzy.view.processor;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import zzy.worker.processor.Mode;

/**
 * A combo box that enables the change of the Processor
 * 
 * @author Zhaoyi
 */
public class ModeComboBox extends JComboBox<Mode> {
	private static final long serialVersionUID = 7751848591815359031L;

	/**
	 * Construct a combo box accronding the modes in the Mode enum class
	 * 
	 * @param parent - the clipboard monitor window
	 */
	public ModeComboBox(ClipboardMonitor parent) {
		super(Mode.values());
		addActionListener((ActionEvent e) -> {
			parent.changeProcessor();
		});
	}
}
