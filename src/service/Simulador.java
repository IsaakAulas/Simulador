package service;

import domain.Resultados;
import telas.TelaResultados;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulador {

    private Outliers outliers = new Outliers();
    public void simular(Integer quantidadeDeAtendentes){
        List<Double> valoresTempoServico = gerarVetorAleatorio();
        List<Double> valoresTempoChegada = gerarVetorAleatorio();
        realizarSimulacao(quantidadeDeAtendentes, valoresTempoServico, valoresTempoChegada);
    }

    public void simular(Integer quantidadeDeAtendentes, ArrayList<Double> valoresTempoServico, ArrayList<Double> valoresTempoChegada){
        ArrayList<Double>[] listasComOutliers = new ArrayList[]{valoresTempoServico, valoresTempoChegada};
        ArrayList<Double>[] listasProntas = outliers.removerOutliers(listasComOutliers);
        ArrayList<Double> valoresSemOutliersTS = listasProntas[0];
        ArrayList<Double> valoresSemOutliersTC = listasProntas[1];
        realizarSimulacao(quantidadeDeAtendentes, valoresSemOutliersTS, valoresSemOutliersTC);
    }

    private void realizarSimulacao(Integer quantidadeDeAtendentes, List<Double> valoresTempoServico, List<Double> valoresTempoChegada){
        Resultados resultados = new Resultados();
        int tempoOcioso = 0;
        int tempoEmEspera = 0;
        int tempoTotalDeAtendimento = 0;
        double tempoMedioDeAtendimento;
        double tempoParaProximaChegada;
        double tempoDeAtendimentoDoServico;
        ArrayList<Double> servicoEmFila = new ArrayList<>();
        ArrayList<Double> atendentesEmTrabalho = new ArrayList<>();

        for (int i = 0; i < valoresTempoChegada.size(); i++){
            tempoParaProximaChegada = valoresTempoChegada.get(i);
            tempoDeAtendimentoDoServico = valoresTempoServico.get(i);
            tempoTotalDeAtendimento = (int) (tempoTotalDeAtendimento + tempoDeAtendimentoDoServico);

            for (int f = 0; f < tempoParaProximaChegada; f++){

                if (servicoEmFila.size() > 0 && quantidadeDeAtendentes == 0){
                    tempoEmEspera = tempoEmEspera + 1;
                }

                if (quantidadeDeAtendentes > 0){
                    tempoOcioso = tempoOcioso + 1;
                }

                for (int x = 0; x < atendentesEmTrabalho.size(); x++){

                    if (quantidadeDeAtendentes > 0 && servicoEmFila.size() > 0){
                        atendentesEmTrabalho.add(servicoEmFila.get(0));
                        servicoEmFila.remove(0);
                    }

                    if (atendentesEmTrabalho.get(x) > 0){
                        double valor = atendentesEmTrabalho.get(x) - 1;
                        atendentesEmTrabalho.set(x, valor);
                    }

                    if (atendentesEmTrabalho.get(x) == 0){
                        atendentesEmTrabalho.remove(x);
                        quantidadeDeAtendentes = quantidadeDeAtendentes + 1;
                    }
                }
            }

            if (quantidadeDeAtendentes == 0){
                servicoEmFila.add(tempoDeAtendimentoDoServico);
            }

            if (quantidadeDeAtendentes > 0){
                atendentesEmTrabalho.add(tempoDeAtendimentoDoServico);
                quantidadeDeAtendentes = quantidadeDeAtendentes - 1;
            }
        }

        tempoMedioDeAtendimento = (double) tempoTotalDeAtendimento / valoresTempoChegada.size();

        resultados.setTempoAguardandoServico(tempoEmEspera);
        resultados.setTempoAtendenteOcioso(tempoOcioso);
        resultados.setTempoMedioDeAtendimento(tempoMedioDeAtendimento);

        SwingUtilities.invokeLater(() -> {
            TelaResultados telaResultados = new TelaResultados(resultados);
            telaResultados.setVisible(true);
            telaResultados.setLocationRelativeTo(null);
        });
    }

    private List<Double> gerarVetorAleatorio() {
        List<Double> valores = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 200; i++) {
            valores.add(Double.parseDouble(String.valueOf(random.nextInt(9 + 1))));
        }

        return valores;
    }
}
