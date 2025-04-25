package br.com.poo.petmatch.animal;

public class Cachorro extends Animal {
    private String pelagem;

    public Cachorro(int id, String nome, String idade, String raca, String porte, String vacinado, String observacoes, String personalidadeAnimal, String tipoMoradia, String tamanhoMoradia, String podeFicarComOutrosPets, String tipoAmbiente, String enderecoImagem, String sexo, boolean animalFicticio, String pelagem) {
        super(id, nome, idade, raca, porte, vacinado, observacoes, personalidadeAnimal, tipoMoradia, tamanhoMoradia, podeFicarComOutrosPets, tipoAmbiente, enderecoImagem, sexo, animalFicticio);
        this.pelagem = pelagem;
    }

    public String getPelagem() {
        return pelagem;
    }
}
