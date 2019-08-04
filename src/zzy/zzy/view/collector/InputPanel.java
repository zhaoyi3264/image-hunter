package zzy.view.collector;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import zzy.document.CharacterDocument;
import zzy.util.Utils;
import zzy.worker.collector.Collector;

/**
 * A input panel for collector window
 * 
 * @author Zhaoyi
 */
public class InputPanel extends JPanel {
	private static final long serialVersionUID = -7320413027934717176L;
	private JTextField url;
	private JTextField clazz;
	private JTextField attr;
	private PlaceHolderPanel place;

	/**
	 * Constuct an input panel using gridbag layout
	 */
	public InputPanel() {
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		setLayout(gb);
		gc.weightx = 1;
		gc.insets = new Insets(3, 0, 3, 0);
		gc.gridheight = 1;
		gc.gridwidth = GridBagConstraints.REMAINDER;

		// combo box
		url = new JTextField();
		clazz = new JTextField(new CharacterDocument(), "", 5);
		attr = new JTextField(new CharacterDocument(), "", 5);
		PresetComboBox combo = new PresetComboBox(clazz, attr);

		gc.weighty = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		Utils.addComponent(this, gb, gc, combo);

		// input area
		gc.gridx = 0;
		gc.weightx = 1;
		gc.gridwidth = 1;
		Utils.addComponent(this, gb, gc, new JLabel("URL to collect URLs from:"));
		Utils.addComponent(this, gb, gc, new JLabel("Select elements by class:"));
		Utils.addComponent(this, gb, gc, new JLabel("Select URLs by attribute:"));

		gc.gridx = 1;
		gc.weightx = 9;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		Utils.addComponent(this, gb, gc, url);
		Utils.addComponent(this, gb, gc, clazz);
		Utils.addComponent(this, gb, gc, attr);

		// enable place holder
		gc.weightx = 1;
		gc.gridx = 0;
		Utils.addComponent(this, gb, gc, place = new PlaceHolderPanel());
	}

	/**
	 * Update the collector with the inputs
	 * 
	 * @param co - the collector tp update
	 * @throws MalformedURLException if errors occur among the input
	 */
	public void updateCollector(Collector co) throws MalformedURLException {
		String u = url.getText();
		String c = clazz.getText();
		String a = attr.getText();

		if (!Pattern.matches("^https://(\\S+\\.)+\\S+", u)) // a simple URL check
			throw new MalformedURLException("Incorrect URL");
		if (c.length() == 0 || a.length() == 0)
			throw new MalformedURLException("Class or attribute name cannot be empty");

		co.setUrl(u);
		co.setClazz(c);
		co.setAttr(a);
		co.setStarEnabled(place.isStar());
		if (place.isStar()) {
			int f, t;
			try {
				f = Integer.parseInt(place.getFrom());
				t = Integer.parseInt(place.getTo());
			} catch (NumberFormatException e) {
				throw new MalformedURLException("Index cannot be empty");
			}
			if (f == 0)
				throw new MalformedURLException("Start index cannot be zero");
			if (t < f)
				throw new MalformedURLException("End index cannot be smaller than start index");
			co.setFrom(f);
			co.setTo(t);
		}
	}
}
