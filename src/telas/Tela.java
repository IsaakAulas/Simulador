package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {
    private TelaCarregarESimular telaCarregarESimular = new TelaCarregarESimular();
    private TelaSimular telaSimular = new TelaSimular();
    private JButton botaoCarregarESimular;
    private JButton botaoSimular;

    public Tela() {
        setTitle("Service.Simulador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 80));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        botaoCarregarESimular = new JButton("Carregar E Simular");
        botaoSimular = new JButton("Simular");

        painelBotoes.add(botaoCarregarESimular);
        painelBotoes.add(botaoSimular);

        add(painelBotoes, BorderLayout.SOUTH);

        botaoCarregarESimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaCarregarESimular.carregarArquivosESimular();
            }
        });

        botaoSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaSimular.gerarTelaSimular();
            }
        });

        pack();
        setVisible(true);
    }

}
