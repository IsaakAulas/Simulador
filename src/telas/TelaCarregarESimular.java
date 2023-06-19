package telas;

import service.Simulador;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TelaCarregarESimular extends Component {

    Simulador simulador = new Simulador();
    ArrayList<Double> valoresTempoServico = new ArrayList<>();
    ArrayList<Double> valoresTempoChegada = new ArrayList<>();
    int quantidadeDeAtendentes;

    protected void carregarArquivosESimular() {
        JFrame carregarArquivos = new JFrame("Carregar Arquivos");
        carregarArquivos.setMinimumSize(new Dimension(900, 120));
        carregarArquivos.setLocationRelativeTo(null);
        carregarArquivos.setLayout(new BorderLayout());

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel labelQuantidadeAtendentes = new JLabel("Quantidade de Atendentes:");
        JTextField textFieldQuantidadeAtendentes = new JTextField(10);

        JButton botaoSelecionarTempoChegada = new JButton("Selecionar Arquivo Tempo de Chegada");
        JButton botaoSelecionarTempoServico = new JButton("Selecionar Arquivo Tempo de Serviço");
        JButton botaoSimular = new JButton("Simular");

        painelBotoes.add(labelQuantidadeAtendentes);
        painelBotoes.add(textFieldQuantidadeAtendentes);
        painelBotoes.add(botaoSelecionarTempoChegada);
        painelBotoes.add(botaoSelecionarTempoServico);

        carregarArquivos.add(painelBotoes, BorderLayout.NORTH);
        carregarArquivos.add(botaoSimular, BorderLayout.SOUTH);

        botaoSelecionarTempoChegada.addActionListener(e -> carregarArquivo("tempoChegada"));

        botaoSelecionarTempoServico.addActionListener(e -> carregarArquivo("tempoServico"));

        botaoSimular.addActionListener(e -> {
            String quantidadeAtendentesText = textFieldQuantidadeAtendentes.getText();
            quantidadeDeAtendentes = Integer.parseInt(quantidadeAtendentesText);
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
                    double valor = Double.parseDouble(line);
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
