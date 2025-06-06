package Financas.Util;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GerenciadorFecharFrame{

    public static void adicionarAcaoAoFechar(JFrame frame, Runnable acaoAoFechar) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                acaoAoFechar.run();
                frame.dispose();
            }
        });
    }
}
