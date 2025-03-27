import javax.swing.*;
import java.awt.*;

class TelaCadastro extends JPanel {
    private FramePrincipal frame;
    private JTextField campoUsuario;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton btnCadastrar;
    private JButton btnVoltar;

    public TelaCadastro(FramePrincipal frame) {
        this.frame = frame;
        setLayout(new GridLayout(5, 2, 5, 5));}}