package br.com.poo.petmatch.usuario;

import br.com.poo.petmatch.animal.Animal;

import java.util.List;

public class Cliente extends Usuario {

    public List<Animal> animaisAdotadosCliente;

    public Cliente(String nome, String cpf, String email, String senha, List<Animal> animaisAdotadosCliente) {
        super(nome, cpf, email, senha);
        this.animaisAdotadosCliente = animaisAdotadosCliente;
    }
}
