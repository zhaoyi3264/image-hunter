package zzy.view.collector;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zzy.document.IntegerDocument;
import zzy.util.Utils;

/**
 * A panel that manage place holder enabling
 * 
 * @author Zhaoyi
 */
public class PlaceHolderPanel extends JPanel {
	private static final long serialVersionUID = 8999125713650455100L;
	private JCheckBox star;
	private JTextField from;
	private JTextField to;

	/**
	 * Construct the panel using girdbag layout
	 */
	public PlaceHolderPanel() {
		star = new JCheckBox("Enable place holder (*)");
		from = new JTextField(new IntegerDocument(), "", 5);
		to = new JTextField(new IntegerDocument(), "", 5);
		from.setEnabled(star.isSelected());
		to.setEnabled(star.isSelected());
		star.addActionListener((ActionEvent e) -> {
			from.setEnabled(star.isSelected());
			to.setEnabled(star.isSelected());
		});

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(gb);

		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(3, 0, 3, 0);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridwidth = 1;

		Utils.addComponent(this, gb, gc, star);
		Utils.addComponent(this, gb, gc, new JLabel());
		Utils.addComponent(this, gb, gc, new JLabel("From"));
		Utils.addComponent(this, gb, gc, from);
		Utils.addComponent(this, gb, gc, new JLabel("To"));
		gc.gridwidth = GridBagConstraints.REMAINDER;
		Utils.addComponent(this, gb, gc, to);
	}

	/**
	 * Return true if the check box is selected
	 * 
	 * @return true if the check box is selected
	 */
	public boolean isStar() {
		return star.isSelected();
	}

	/**
	 * Return the integer in the text area
	 * 
	 * @return the integer in the text area
	 */
	public String getFrom() {
		return from.getText();
	}

	/**
	 * Return the integer in the text area
	 * 
	 * @return the integer in the text area
	 */
	public String getTo() {
		return to.getText();
	}
}
