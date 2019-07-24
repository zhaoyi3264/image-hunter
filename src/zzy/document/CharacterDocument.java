package zzy.document;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CharacterDocument extends PlainDocument {
	private static final long serialVersionUID = -4173432449092053148L;

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if (Pattern.matches("[a-z-_ ]+", str))
			super.insertString(offs, str, a);
		return;
	}
}
