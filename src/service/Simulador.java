package service;

import domain.Resultados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulador {
    protected Resultados resultados;

    public void simular(int quantidadeDeAtendentes){
        List<Integer> valoresTempoServico = gerarVetorAleatorio();
        List<Integer> valoresTempoChegada = gerarVetorAleatorio();
        realizarSimulacao(quantidadeDeAtendentes, valoresTempoServico, valoresTempoChegada);
    }

    public void simular(int quantidadeDeAtendentes, List<Integer> valoresTempoServico, List<Integer> valoresTempoChegada){
        realizarSimulacao(quantidadeDeAtendentes, valoresTempoServico, valoresTempoChegada);
    }

    private void realizarSimulacao(int quantidadeDeAtendentes, List<Integer> valoresTempoServico, List<Integer> valoresTempoChegada){
        System.out.println("chegou aqui:");
        System.out.println("valoresTempoChegada ||| valoresTempoServico");
        for (int i =0;i<valoresTempoServico.size();i++) {
            System.out.println("           " + valoresTempoChegada.get(i) + "                    " + valoresTempoServico.get(i));
        }
        System.out.println("quantidade de atendentes: "+quantidadeDeAtendentes);

        
//        int tempoAguardandoServico = valoresTempoChegada.get(0);
//        int tempoAtendenteOcioso = 0;
//        List<Integer> atendentes = new ArrayList<>();
//
//        for (int i = 0; i < valoresTempoChegada.size(); i++) {
//            int tempoProximaChegada;
//            int tempoAtualChegada = valoresTempoChegada.get(i);
//            int tempoRealizacaoDeServico = valoresTempoServico.get(i);
//
//            if (i == (valoresTempoChegada.size() - 2)){
//                tempoProximaChegada = valoresTempoChegada.get(i);
//            } else {
//                tempoProximaChegada = -1;
//            }
//            Collections.sort(atendentes);
//            // Verifica se há atendentes disponíveis
//            if (atendentes.size() < quantidadeDeAtendentes) {
//                // Adiciona o atendente e subtrai o tempo de serviço
//                atendentes.add(tempoRealizacaoDeServico);
//            } else {
//                // Calcula o tempo de espera
//                int menorTempoServico = atendentes.get(0);
//
//                for (int j = 0; j < atendentes.size(); j++) {
//                    if (menorTempoServico < tempoProximaChegada) {
//                        int tempo = atendentes.get(j) - tempoProximaChegada;
//                        atendentes.set(j, tempo);
//                    }
//                }
//                // Remove os atendentes que concluíram o serviço
//                for (int x = 0; x < atendentes.size(); x++) {
//                    if (atendentes.get(x) <= 0) {
//                        atendentes.remove(x);
//                        x--;
//                    }
//                }
//
//                int tempoEspera = tempoAtualChegada - menorTempoServico;
//                if (tempoEspera > 0) {
//                    tempoDeEspera += tempoEspera;
//                }
//
//                // Subtrai o tempo de serviço de todos os atendentes
//                for (int j = 0; j < atendentes.size(); j++) {
//                    atendentes.set(j, atendentes.get(j) - tempoRealizacaoDeServico);
//                }
//            }
//
//            // Calcula o tempo ocioso
//            int atendentesDisponiveis = quantidadeDeAtendentes - atendentes.size();
//            if (atendentesDisponiveis > 0) {
//                tempoOcioso += atendentesDisponiveis * tempoRealizacaoDeServico;
//            }
//        }
//
//        System.out.println("Tempo de Espera: " + tempoDeEspera);
//        System.out.println("Tempo Ocioso: " + tempoOcioso);
    }

    private List<Integer> gerarVetorAleatorio() {
        List<Integer> valores = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 200; i++) {
            valores.add(random.nextInt(9 + 1));
        }

        return valores;
    }
}
