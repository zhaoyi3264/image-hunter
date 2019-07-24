package zzy.document;

import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class IntegerDocument extends PlainDocument{
	private static final long serialVersionUID = 2318945940447168386L;

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(offs <= 8 && Pattern.matches("\\d+", str)) {
			super.insertString(offs, str, a);
		}
	}
}
