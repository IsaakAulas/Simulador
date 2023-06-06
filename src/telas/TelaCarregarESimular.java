package telas;

import service.Simulador;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelaCarregarESimular extends Component {

    Simulador simulador = new Simulador();
    List<Integer> valoresTempoServico = new ArrayList<>();
    List<Integer> valoresTempoChegada = new ArrayList<>();
    int quantidadeDeAtendentes;

    protected void carregarArquivosESimular() {
        JFrame carregarArquivos = new JFrame("Carregar Arquivos");
        carregarArquivos.setMinimumSize(new Dimension(1000, 100));
        carregarArquivos.setLocationRelativeTo(null);
        carregarArquivos.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel labelQuantidadeAtendentes = new JLabel("Quantidade de Atendentes:");
        JTextField textFieldQuantidadeAtendentes = new JTextField(10);

        JButton botaoConfirmar = new JButton("Confirmar");
        JButton botaoSelecionarTempoChegada = new JButton("Selecionar Arquivo Tempo de Chegada");
        JButton botaoSelecionarTempoServico = new JButton("Selecionar Arquivo Tempo de Serviço");
        JButton botaoConcluirSimulacao = new JButton("Concluir Simulação");

        painelBotoes.add(labelQuantidadeAtendentes);
        painelBotoes.add(textFieldQuantidadeAtendentes);
        painelBotoes.add(botaoConfirmar);
        painelBotoes.add(botaoSelecionarTempoChegada);
        painelBotoes.add(botaoSelecionarTempoServico);
        painelBotoes.add(botaoConcluirSimulacao);

        carregarArquivos.add(painelBotoes, BorderLayout.SOUTH);

        botaoConfirmar.addActionListener(e -> {
            String quantidadeAtendentesText = textFieldQuantidadeAtendentes.getText();
            quantidadeDeAtendentes = Integer.parseInt(quantidadeAtendentesText);
        });

        botaoSelecionarTempoChegada.addActionListener(e -> carregarArquivo("tempoChegada"));

        botaoSelecionarTempoServico.addActionListener(e -> carregarArquivo("tempoServico"));

        botaoConcluirSimulacao.addActionListener(e -> {
            carregarArquivos.dispose();
            simulador.simular(quantidadeDeAtendentes, valoresTempoServico, valoresTempoChegada);
        });

        carregarArquivos.pack();
        carregarArquivos.setVisible(true);
    }

    private void carregarArquivo(String vetorNome) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    int valor = Integer.parseInt(line);
                    if (vetorNome.equals("tempoServico")){
                        valoresTempoServico.add(valor);
                    } else if (vetorNome.equals("tempoChegada")) {
                        valoresTempoChegada.add(valor);
                    }

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
