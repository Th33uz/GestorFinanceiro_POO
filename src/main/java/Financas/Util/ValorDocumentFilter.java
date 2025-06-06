package Financas.Util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ValorDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(oldText);
        newText.replace(offset, offset + length, text);
        if (isValid(newText.toString())) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(oldText);
        newText.delete(offset, offset + length);
        if (isValid(newText.toString())) {
            super.remove(fb, offset, length);
        }
    }

    private boolean isValid(String text) {
        if (text.isEmpty()) return true;
        // Só números e uma vírgula
        if (!text.matches("^\\d*(,\\d*)?$")) return false;
        // No máximo dois dígitos após a vírgula
        int idx = text.indexOf(',');
        if (idx >= 0 && text.length() - idx - 1 > 2) return false;
        return true;
    }
}
 