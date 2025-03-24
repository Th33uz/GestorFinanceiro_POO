import javax.swing.*;
import java.awt.*;

class FramePrincipal extends JFrame {
    private CardLayout cardLayout;
    private JPanel container;

    public FramePrincipal() {
        setTitle("Sistema de Gestão Financeira");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        add(container);
    }
}

