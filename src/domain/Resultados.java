package domain;

public class Resultados {
    private int tempoAguardandoServico;

    public int getTempoAguardandoServico() {
        return tempoAguardandoServico;
    }

    public void setTempoAguardandoServico(int tempoAguardandoServico) {
        this.tempoAguardandoServico = tempoAguardandoServico;
    }

    public int getTempoAtendenteOcioso() {
        return tempoAtendenteOcioso;
    }

    public void setTempoAtendenteOcioso(int tempoAtendenteOcioso) {
        this.tempoAtendenteOcioso = tempoAtendenteOcioso;
    }

    public double getTempoMedioDeAtendimento() {
        return tempoMedioDeAtendimento;
    }

    public void setTempoMedioDeAtendimento(double tempoMedioDeAtendimento) {
        this.tempoMedioDeAtendimento = tempoMedioDeAtendimento;
    }

    private int tempoAtendenteOcioso;
    private double tempoMedioDeAtendimento;
}
