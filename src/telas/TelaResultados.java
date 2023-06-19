package telas;

import domain.Resultados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaResultados extends JFrame {
    public TelaResultados(Resultados resultado) {
        setTitle("Resultados");
        setSize(900, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 linhas, 1 coluna, espaçamento horizontal e vertical de 10 pixels

        JLabel labelTempoAguardandoServico = new JLabel("TEMPO AGUARDANDO SERVIÇO: " + resultado.getTempoAguardandoServico());
        labelTempoAguardandoServico.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(labelTempoAguardandoServico);

        JLabel labelTempoAtendenteOcioso = new JLabel("TEMPO DE ATENDENTES OCIOSOS: " + resultado.getTempoAtendenteOcioso());
        labelTempoAtendenteOcioso.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(labelTempoAtendenteOcioso);

        JLabel labelTempoMedioDeAtendimento = new JLabel("TEMPO MÉDIO POR ATENDIMENTO: " + resultado.getTempoMedioDeAtendimento());
        labelTempoMedioDeAtendimento.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(labelTempoMedioDeAtendimento);

        add(panel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Concluir Simulação");
        closeButton.setFont(new Font("Arial", Font.BOLD, 18));
        add(closeButton, BorderLayout.SOUTH);

        closeButton.addActionListener(e -> dispose());
    }
}
