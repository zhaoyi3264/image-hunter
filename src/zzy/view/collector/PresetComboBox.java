package zzy.view.collector;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import zzy.worker.collector.Preset;

/**
 * A combo box that allows the change of collection presets
 * 
 * @author Zhaoyi
 */
public class PresetComboBox extends JComboBox<Preset> {
	private static final long serialVersionUID = 1860844669006904227L;
	private JTextField clazz;
	private JTextField attr;

	/**
	 * Construct a combo box with presets
	 * 
	 * @param clazz - the class name text field
	 * @param attr  - the attribute name text field
	 */
	public PresetComboBox(JTextField clazz, JTextField attr) {
		super(Preset.values());
		this.clazz = clazz;
		this.attr = attr;
		addActionListener((ActionEvent e) -> {
			switch ((Preset) getSelectedItem()) {
				case Customed:
					setTextFields(true, "", "");
					break;
				case Wallhaven:
					setTextFields(false, "preview", "href");
					break;
				case Yandere:
					// TODO: directlink smallimg
					setTextFields(false, "directlink largeimg", "href");
					break;
			}
		});
	}

	/**
	 * Enable text fields and set texts
	 * 
	 * @param enabled - enable the text field
	 * @param c       - the class name
	 * @param a       - the attribute name
	 */
	private void setTextFields(boolean enabled, String c, String a) {
		clazz.setEnabled(enabled);
		attr.setEnabled(enabled);
		clazz.setText(c);
		attr.setText(a);
	}
}
