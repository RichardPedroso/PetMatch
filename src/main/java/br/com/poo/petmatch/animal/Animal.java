package br.com.poo.petmatch.animal;

public abstract class Animal {
    public static int nextId = 1;
    private int id;
    private String nome;
    private String idade;
    private String raca;
    private String porte;
    private String vacinado;
    private String observacoes;
    private String personalidadeAnimal;
    private String tipoMoradia;
    private String tamanhoMoradia;
    private String podeFicarComOutrosPets;
    private String tipoAmbiente;
    private String enderecoImagem;
    private String sexo;
    private boolean animalFicticio;

    public Animal(int id, String nome, String idade, String raca, String porte, String vacinado, String observacoes, String personalidadeAnimal, String tipoMoradia, String tamanhoMoradia, String podeFicarComOutrosPets, String tipoAmbiente, String enderecoImagem, String sexo, boolean animalFicticio) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.porte = porte;
        this.vacinado = vacinado;
        this.observacoes = observacoes;
        this.personalidadeAnimal = personalidadeAnimal;
        this.tipoMoradia = tipoMoradia;
        this.tamanhoMoradia = tamanhoMoradia;
        this.podeFicarComOutrosPets = podeFicarComOutrosPets;
        this.tipoAmbiente = tipoAmbiente;
        this.enderecoImagem = enderecoImagem;
        this.sexo = sexo;
        this.animalFicticio = animalFicticio;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdade() {
        return idade;
    }

    public String getRaca() {
        return raca;
    }

    public String getPorte() {
        return porte;
    }

    public String getVacinado() {
        return vacinado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getPersonalidadeAnimal() {
        return personalidadeAnimal;
    }

    public String getTipoMoradia() {
        return tipoMoradia;
    }

    public String getTamanhoMoradia() {
        return tamanhoMoradia;
    }

    public String getPodeFicarComOutrosPets() {
        return podeFicarComOutrosPets;
    }

    public String getTipoAmbiente() {
        return tipoAmbiente;
    }

    public String getEnderecoImagem() {
        return enderecoImagem;
    }

    public void setEnderecoImagem(String enderecoImagem) {
        this.enderecoImagem = enderecoImagem;
    }

    public String getSexo() {
        return sexo;
    }

    public boolean isAnimalFicticio() {
        if(animalFicticio){
            return true;
        }else{
            return false;
        }
    }
}
