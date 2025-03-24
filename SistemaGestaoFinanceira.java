import javax.swing.*;

public class SistemaGestaoFinanceira {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FramePrincipal frame = new FramePrincipal();
            frame.setVisible(true);
        });
    }
}