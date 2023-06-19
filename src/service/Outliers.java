package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Outliers {

    public ArrayList<Double>[] removerOutliers(ArrayList<Double>[] listas){
        ArrayList<Double> valoresComOutliersTS = listas[0];
        ArrayList<Double> valoresComOutliersTC = listas[1];
        ArrayList<Double> valoresARemoverTS = outliersARemover(new ArrayList<>(valoresComOutliersTS));
        ArrayList<Double> valoresARemoverTC = outliersARemover(new ArrayList<>(valoresComOutliersTC));
        ArrayList<Double> valoresSemOutliersTC = new ArrayList<>();
        ArrayList<Double> valoresSemOutliersTS = new ArrayList<>();

        for (int i = 0; i < valoresComOutliersTS.size(); i++) {
            Double valor = valoresComOutliersTS.get(i);
            for (Double valorARemover : valoresARemoverTS) {
                if (Objects.equals(valor, valorARemover)) {
                    valoresComOutliersTS.remove(i);
                    valoresComOutliersTC.remove(i);
                }
            }
        }

        for (int i = 0; i < valoresComOutliersTC.size(); i++) {
            Double valor = valoresComOutliersTC.get(i);
            for (Double valorARemover : valoresARemoverTC) {
                if (Objects.equals(valor, valorARemover)) {
                    valoresComOutliersTS.remove(i);
                    valoresComOutliersTC.remove(i);
                }
            }
        }
        for(int i = 0; i < 200 && valoresSemOutliersTS.size() < valoresComOutliersTS.size(); i++){
            valoresSemOutliersTS.add(valoresComOutliersTS.get(i));
            valoresSemOutliersTC.add(valoresComOutliersTC.get(i));
        }
        return new ArrayList[]{valoresSemOutliersTS, valoresSemOutliersTC};
    }
    public ArrayList<Double> outliersARemover(ArrayList<Double> listaAnalizada) {
        double[] quartiles = encontrarQuartis(listaAnalizada);
        double quartilInferior = quartiles[0];
        double quartilSuperior = quartiles[1];
        double limite = (quartilSuperior - quartilInferior) * 3;
        ArrayList<Double> valoresARemover = new ArrayList<>();

        for (int i = listaAnalizada.size() - 1; i >= 0; i--) {
            double valor = listaAnalizada.get(i);
            if (Math.min(Math.abs(valor - quartilInferior), Math.abs(valor - quartilSuperior)) > limite)
                valoresARemover.add(valor);
        }
        return valoresARemover;
    }

    private double[] encontrarQuartis(List<Double> listaAnalizada) {
        Collections.sort(listaAnalizada);
        List<Double> quartilInferior = listaAnalizada.subList(0, (int) Math.floor(listaAnalizada.size() / 2.0 - 1));
        List<Double> quartilSuperior = listaAnalizada.subList((int) Math.ceil(listaAnalizada.size() / 2.0), listaAnalizada.size() - 1);

        return new double[] { calcularMedia(quartilInferior), calcularMedia(quartilSuperior) };
    }

    private double calcularMedia(List<Double> data) {
        if (data.size() % 2 != 0)
            return data.get(data.size() / 2);
        else
            return (data.get(data.size() / 2) + data.get((data.size() / 2) + 1)) / 2.0;
    }
}
