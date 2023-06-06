package telas;

import service.Simulador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSimular {
    Simulador simulador = new Simulador();
    int quantidadeDeAtendentes;
    protected void gerarTelaSimular(){
        JFrame realizarSimulação = new JFrame("Realizar Simulação");
        realizarSimulação.setMinimumSize(new Dimension(1000, 100));
        realizarSimulação.setLocationRelativeTo(null);
        realizarSimulação.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel labelQuantidadeAtendentes = new JLabel("Quantidade de Atendentes:");
        JTextField textFieldQuantidadeAtendentes = new JTextField(10);
        JButton botaoConfirmar = new JButton("Confirmar");

        painelBotoes.add(labelQuantidadeAtendentes);
        painelBotoes.add(textFieldQuantidadeAtendentes);
        painelBotoes.add(botaoConfirmar);

        realizarSimulação.add(painelBotoes, BorderLayout.SOUTH);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quantidadeAtendentesText = textFieldQuantidadeAtendentes.getText();
                quantidadeDeAtendentes = Integer.parseInt(quantidadeAtendentesText);
                realizarSimulação.dispose();
                simulador.simular(quantidadeDeAtendentes);
            }
        });

        realizarSimulação.pack();
        realizarSimulação.setVisible(true);
    }
}
