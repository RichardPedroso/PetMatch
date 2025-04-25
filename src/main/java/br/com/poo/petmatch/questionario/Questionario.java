package br.com.poo.petmatch.questionario;

public class Questionario {

    private String personalidadeAnimal;
    private String tipoMoradia;
    private String pelagem;
    private String tamanhoMoradia;
    private String podeFicarComOutrosPets;
    private String sexo;
    private String tipoAmbiente;

    public Questionario(String personalidadeAnimal, String tipoMoradia, String pelagem, String tamanhoMoradia, String podeFicarComOutrosPets, String sexo, String tipoAmbiente) {
        this.personalidadeAnimal = personalidadeAnimal;
        this.tipoMoradia = tipoMoradia;
        this.pelagem = pelagem;
        this.tamanhoMoradia = tamanhoMoradia;
        this.podeFicarComOutrosPets = podeFicarComOutrosPets;
        this.sexo = sexo;
        this.tipoAmbiente = tipoAmbiente;
    }

    public String getPersonalidadeAnimal() {
        return personalidadeAnimal;
    }

    public String getTipoMoradia() {
        return tipoMoradia;
    }

    public String getPelagem() {
        return pelagem;
    }

    public String getTamanhoMoradia() {
        return tamanhoMoradia;
    }

    public String getPodeFicarComOutrosPets() {
        return podeFicarComOutrosPets;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTipoAmbiente() {
        return tipoAmbiente;
    }
}
