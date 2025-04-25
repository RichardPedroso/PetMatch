package br.com.poo.petmatch.controlePetMatch;

import br.com.poo.petmatch.animal.Animal;
import br.com.poo.petmatch.animal.Cachorro;
import br.com.poo.petmatch.main.PetMatch;
import br.com.poo.petmatch.questionario.Questionario;
import br.com.poo.petmatch.usuario.Administrador;
import br.com.poo.petmatch.usuario.Cliente;
import br.com.poo.petmatch.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

import static br.com.poo.petmatch.animal.Animal.nextId;

public class ControlePetMatch {

    public List<Usuario> usuarios;
    public List<Animal> animais;
    public List<Animal> animaisCompativeis;
    public List<Animal> relatorioAnimaisAdotados;
    public Usuario usuarioLogado;

    public ControlePetMatch() {
        this.usuarios = new ArrayList<>();
        this.animais = new ArrayList<>();
        this.animaisCompativeis = new ArrayList<>();
        this.relatorioAnimaisAdotados = new ArrayList<>();
    }

    public void cadastrarAnimal(Animal animal) {
        animais.add(animal);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void excluirAnimalPorId(int id) {

        for (Animal animal : PetMatch.controlePetMatch.animais) {
            if (animal.getId() == id) {
                PetMatch.controlePetMatch.animais.remove(animal);
                break;
            }
        }
    }

    public void excluirUsuarioPorCpf(String cpf) {

        for (Usuario usuario : PetMatch.controlePetMatch.usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                PetMatch.controlePetMatch.usuarios.remove(usuario);
                break;
            }
        }
    }

    public Usuario verificarLoginUsuario(String caixaEmail, String caixaSenha) {
        usuarioLogado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(caixaEmail) && usuario.getSenha().equals(caixaSenha)) {
                usuarioLogado = usuario;
                break;
            }
        }
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        Cliente cliente = (Cliente) usuarioLogado;
        this.usuarioLogado = cliente;
        PetMatch.setCliente(cliente);
    }

    public void adicionarAnimaisPorMatch(Questionario questionario) {
        boolean desconsiderarSexo = false;
        boolean desconsiderarPelagem = false;
        boolean considerarTodasRespostas = false;

        String personalidade = questionario.getPersonalidadeAnimal().toLowerCase();
        String moradia = questionario.getTipoMoradia().toLowerCase();
        String tamanhoMoradia = questionario.getTamanhoMoradia().toLowerCase();
        String podeFicarComOutrosPets = questionario.getPodeFicarComOutrosPets().toLowerCase();
        String sexo = questionario.getSexo().toLowerCase();
        String ambiente = questionario.getTipoAmbiente().toLowerCase();
        String pelagem = questionario.getPelagem().toLowerCase();

        if (sexo.contains("não") || sexo.contains("nao")) {
            desconsiderarSexo = true;
        }
        if (pelagem.contains("não") || pelagem.contains("nao")) {
            desconsiderarPelagem = true;
        }
        if (!desconsiderarSexo && !desconsiderarPelagem) {
            considerarTodasRespostas = true;
        }

        for (Animal animal : animais) {
            if (animal instanceof Cachorro cachorro) {
                boolean corresponde = true;

                if (desconsiderarSexo && desconsiderarPelagem) {

                    if (!cachorro.getPersonalidadeAnimal().toLowerCase().contains(personalidade)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoMoradia().toLowerCase().contains(moradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTamanhoMoradia().toLowerCase().contains(tamanhoMoradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getPodeFicarComOutrosPets().toLowerCase().contains(podeFicarComOutrosPets)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoAmbiente().toLowerCase().equals(ambiente)) {
                        corresponde = false;
                    }

                } else if (desconsiderarSexo) {

                    if (!cachorro.getPersonalidadeAnimal().toLowerCase().contains(personalidade)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoMoradia().toLowerCase().contains(moradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTamanhoMoradia().toLowerCase().contains(tamanhoMoradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getPodeFicarComOutrosPets().toLowerCase().contains(podeFicarComOutrosPets)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoAmbiente().toLowerCase().equals(ambiente)) {
                        corresponde = false;
                    }
                    if (!cachorro.getPelagem().toLowerCase().equals(pelagem)) {
                        corresponde = false;
                    }

                } else if (desconsiderarPelagem) {

                    if (!cachorro.getPersonalidadeAnimal().toLowerCase().contains(personalidade)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoMoradia().toLowerCase().contains(moradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTamanhoMoradia().toLowerCase().contains(tamanhoMoradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getPodeFicarComOutrosPets().toLowerCase().contains(podeFicarComOutrosPets)) {
                        corresponde = false;
                    }
                    if (!cachorro.getSexo().toLowerCase().contains(sexo)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoAmbiente().toLowerCase().equals(ambiente)) {
                        corresponde = false;
                    }

                } else if (considerarTodasRespostas) {

                    if (!cachorro.getPersonalidadeAnimal().toLowerCase().contains(personalidade)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoMoradia().toLowerCase().contains(moradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTamanhoMoradia().toLowerCase().contains(tamanhoMoradia)) {
                        corresponde = false;
                    }
                    if (!cachorro.getPodeFicarComOutrosPets().toLowerCase().contains(podeFicarComOutrosPets)) {
                        corresponde = false;
                    }
                    if (!cachorro.getSexo().toLowerCase().contains(sexo)) {
                        corresponde = false;
                    }
                    if (!cachorro.getTipoAmbiente().toLowerCase().equals(ambiente)) {
                        corresponde = false;
                    }
                    if (!cachorro.getPelagem().toLowerCase().equals(pelagem)) {
                        corresponde = false;
                    }
                }
                if (corresponde) {
                    animaisCompativeis.add(animal);
                }
            }
        }
    }

    public void instanciarUsuarios(){

        Usuario cliente1 = new Cliente("Client", "12345678911", "c", "c", new ArrayList<>());
        cadastrarUsuario(cliente1);

        Usuario administrador1 = new Administrador("Admin", "12345678910", "a", "a");
        cadastrarUsuario(administrador1);
    }

    public void instanciarAnimais() {

        Animal cachorro1 = new Cachorro(nextId++, "Bisquí", "4 anos", "Pitbull", "Médio", "Sim", "Castrado", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro1.jpg", "Macho", false, "curta");
        cadastrarAnimal(cachorro1);

        Animal cachorro2 = new Cachorro(nextId++, "Morgana", "1 ano", "Pitbull", "Médio", "Sim", "Castrada", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro2.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro2);

        Animal cachorro3 = new Cachorro(nextId++, "Turco", "5 anos", "Pitbull", "Grande", "Sim", "Castrada", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro3.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro3);

        Animal cachorro4 = new Cachorro(nextId++, "Floquinho", "1 ano", "Não definida", "Médio", "Sim", "Castrada", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro4.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro4);

        Animal cachorro5 = new Cachorro(nextId++, "Cramela", "2 anos", "Não definida", "Médio", "Sim", "Castrada", "aventureiro | aventureira", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro5.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro5);

        Animal cachorro6 = new Cachorro(nextId++, "Molly", "2 anos", "Não definida", "Pequeno", "Sim", "Castrada", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro6.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro6);

        Animal cachorro7 = new Cachorro(nextId++, "Nat", "1 ano", "Não definida", "Médio", "Sim", "Castrada", "aventureiro | aventureira", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro7.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro7);

        Animal cachorro8 = new Cachorro(nextId++, "Snoop", "1 ano", "Não definida", "Médio", "Sim", "Não cadastrada", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro8.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro8);

        Animal cachorro9 = new Cachorro(nextId++, "Cookie", "4 anos", "Não definida", "Grande", "Sim", "Castrado", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "calmo", "cachorro9.jpg", "Macho", false, "curta");
        cadastrarAnimal(cachorro9);

        Animal cachorro10 = new Cachorro(nextId++, "Thor", "8 anos", "Border Collie", "Médio", "Sim", "Bravo", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro10.jpg", "Macho", false, "longa");
        cadastrarAnimal(cachorro10);

        Animal cachorro11 = new Cachorro(nextId++, "Algodinho", "1 ano", "Yorkshire", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa", "pequena | media | média | grande", "sim", "agitado", "cachorro11.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro11);

        Animal cachorro12 = new Cachorro(nextId++, "Felpito", "3 anos", "Yorkshire", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa", "pequena | media | média | grande", "sim", "agitado", "cachorro12.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro12);

        Animal cachorro13 = new Cachorro(nextId++, "Pluffy", "2 anos", "Cocker", "Médio", "Sim", "", "aventureiro | aventureira", "casa", "media | média | grande", "sim", "calmo", "cachorro13.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro13);

        Animal cachorro14 = new Cachorro(nextId++, "Pluffy", "2 anos", "Cocker", "Médio", "Sim", "", "aventureiro | aventureira", "casa", "media | média | grande", "não | nao", "calmo", "cachorro14.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro14);

        Animal cachorro15 = new Cachorro(nextId++, "Salsicha", "2 anos", "Beagle", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim | sim", "calmo", "cachorro15.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro15);

        Animal cachorro16 = new Cachorro(nextId++, "Bananinha", "1 ano", "Beagle", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim | sim", "calmo", "cachorro16.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro16);

        Animal cachorro17 = new Cachorro(nextId++, "Benny", "2 anos", "Beagle", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro17.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro17);

        Animal cachorro18 = new Cachorro(nextId++, "Birdie", "2 anos", "Beagle", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro18.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro18);

        Animal cachorro19 = new Cachorro(nextId++, "Charly", "6 anos", "Beagle", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro19.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro19);

        Animal cachorro20 = new Cachorro(nextId++, "Renny", "2 anos", "Beagle", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro20.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro20);

        Animal cachorro21 = new Cachorro(nextId++, "Jully", "2 anos", "Beagle", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "agitado", "cachorro21.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro21);

        Animal cachorro22 = new Cachorro(nextId++, "Chopp", "2 anos", "Rottweiler", "Grande", "Sim", "", "calmo | calma", "casa", "grande", "não | nao", "calmo", "cachorro22.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro22);

        Animal cachorro23 = new Cachorro(nextId++, "Puddy", "1 ano", "Labrador", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "sim", "agitado", "cachorro23.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro23);

        Animal cachorro24 = new Cachorro(nextId++, "Kessie", "2 anos", "Labrador", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "sim", "agitado", "cachorro24.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro24);

        Animal cachorro25 = new Cachorro(nextId++, "Bilu", "2 anos", "Pitbull", "Grande", "Sim", "Nao Castrado", "brincalhao | brincalhão | brincalhona", "casa", "media | medio | média | médio | grande", "sim", "calmo", "cachorro25.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro25);

        Animal cachorro26 = new Cachorro(nextId++, "Pipoca", "1 ano", "Chihuahua", "Pequeno", "Sim", "Castrada", "aventureiro | aventureira", "casa", "pequena | media | medio | média | médio | grande", "não | nao", "agitado", "cachorro26.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro26);

        Animal cachorro27 = new Cachorro(nextId++, "Bagu", "1 ano", "Golden-Retriever", "Grande", "Sim", "Nao Castrado", "aventureiro | aventureira", "casa", "grande", "sim", "agitado", "cachorro27.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro27);

        Animal cachorro28 = new Cachorro(nextId++, "Popo", "1 ano", "Poodle", "Pequeno", "Sim", "Castrado", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro28.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro28);

        Animal cachorro29 = new Cachorro(nextId++, "Clait", "2 anos", "Border Collie", "Grande", "Sim", "Castrada", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro29.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro29);

        Animal cachorro30 = new Cachorro(nextId++, "Dudu", "1 ano", "Golden-Retriever", "Grande", "Sim", "Castrado", "calmo | calma", "casa", "grande", "sim", "calmo", "cachorro30.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro30);

        Animal cachorro31 = new Cachorro(nextId++, "Pipa", "4 anos", "Golden-Retriever", "Grande", "Sim", "Nao Castrada", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro31.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro31);

        Animal cachorro32 = new Cachorro(nextId++, "Puffy", "3 anos", "Labrador", "Grande", "Sim", "Nao Castrada", "aventureiro | aventureira", "casa", "grande", "nao", "agitado", "cachorro32.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro32);

        Animal cachorro33 = new Cachorro(nextId++, "Robin", "4 anos", "Shih Tzu", "Pequeno", "Sim", "Nao Castrado", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro33.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro33);

        Animal cachorro34 = new Cachorro(nextId++, "Rigby", "2 anos", "Shih Tzu", "Pequeno", "Sim", "Castrada", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro34.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro34);

        Animal cachorro35 = new Cachorro(nextId++, "Mel", "4 anos", "Maltês", "Pequeno", "Sim", "Nao Castrada", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro35.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro35);

        Animal cachorro36 = new Cachorro(nextId++, "Lulu", "2 anos", "Maltês", "Pequeno", "Sim", "Castrado", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro36.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro36);

        Animal cachorro37 = new Cachorro(nextId++, "Lola", "4 anos", "Maltês", "Pequeno", "Sim", "Nao Castrada", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro37.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro37);

        Animal cachorro38 = new Cachorro(nextId++, "Kaka", "4 anos", "Maltês", "Pequeno", "Sim", "Nao castrado", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro38.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro38);

        Animal cachorro39 = new Cachorro(nextId++, "Biscoito", "2 anos", "Lulu da Polmerania", "Pequeno", "Sim", "Nao castrado", "brincalhao | brincalhão | brincalhona", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro39.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro39);

        Animal cachorro40 = new Cachorro(nextId++, "Bibi", "2 anos", "Lulu da Polmerania", "Pequeno", "Sim", "Nao castrada", "brincalhao | brincalhão | brincalhona", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro40.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro40);

        Animal cachorro41 = new Cachorro(nextId++, "Basquet", "2 anos", "Lulu da Polmerania", "Pequeno", "Sim", "Nao castrado", "brincalhao | brincalhão | brincalhona", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro41.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro41);

        Animal cachorro42 = new Cachorro(nextId++, "Plumo", "2 anos", "Labrador", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro42.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro42);

        Animal cachorro43 = new Cachorro(nextId++, "Habi", "2 anos", "Pug", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro43.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro43);

        Animal cachorro44 = new Cachorro(nextId++, "Poppo", "1 ano", "Pug", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro44.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro44);

        Animal cachorro45 = new Cachorro(nextId++, "Pepe", "1 ano", "Poodle", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "agitado", "cachorro45.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro45);

        Animal cachorro46 = new Cachorro(nextId++, "Bob", "1 ano", "Bulldog Frances", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro46.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro46);

        Animal cachorro47 = new Cachorro(nextId++, "Pepe", "1 ano", "Bulldog Frances", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro47.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro47);

        Animal cachorro48 = new Cachorro(nextId++, "Buba", "1 ano", "Bulldog Frances", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "agitado", "cachorro48.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro48);

        Animal cachorro49 = new Cachorro(nextId++, "Apollo", "4 anos", "Husky", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "grande", "sim", "agitado", "cachorro49.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro49);

        Animal cachorro50 = new Cachorro(nextId++, "Nubbys", "2 anos", "Doberman", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro50.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro50);

        Animal cachorro51 = new Cachorro(nextId++, "Égis", "2 anos", "Doberman", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro51.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro51);

        Animal cachorro52 = new Cachorro(nextId++, "Hunter", "3 anos", "Husky", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "grande", "sim", "agitado", "cachorro52.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro52);

        Animal cachorro53 = new Cachorro(nextId++, "Gaia", "3 anos", "Husky", "Grande", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "grande", "sim", "agitado", "cachorro53.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro53);

        Animal cachorro54 = new Cachorro(nextId++, "Rex", "1 ano", "Beagle", "Pequeno", "Sim", "", "calmo | calma", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro54.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro54);

        Animal cachorro55 = new Cachorro(nextId++, "Buddy", "3 anos", "Labrador", "Médio", "Sim", "", "aventureiro | aventureira", "casa", "media | medio | média | médio | grande", "não | nao", "agitado", "cachorro55.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro55);

        Animal cachorro56 = new Cachorro(nextId++, "Bella", "2 anos", "Bulldog", "Médio", "Sim", "", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro56.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro56);

        Animal cachorro57 = new Cachorro(nextId++, "Luna", "4 anos", "Chihuahua", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro57.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro57);

        Animal cachorro58 = new Cachorro(nextId++, "Rocky", "2 anos", "Pug", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "agitado", "cachorro58.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro58);

        Animal cachorro59 = new Cachorro(nextId++, "Daisy", "1 ano", "Shih Tzu", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro59.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro59);

        Animal cachorro60 = new Cachorro(nextId++, "Max", "3 anos", "Beagle", "Médio", "Sim", "", "aventureiro | aventureira", "casa", "media | medio | média | médio | grande", "sim", "agitado", "cachorro60.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro60);

        Animal cachorro61 = new Cachorro(nextId++, "Milo", "5 anos", "Boxer", "Médio", "Sim", "", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro61.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro61);

        Animal cachorro62 = new Cachorro(nextId++, "Zoe", "2 anos", "Dachshund", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro62.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro62);

        Animal cachorro63 = new Cachorro(nextId++, "Bailey", "4 anos", "Labrador", "Grande", "Sim", "", "calmo | calma", "casa", "grande", "sim", "calmo", "cachorro63.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro63);

        Animal cachorro64 = new Cachorro(nextId++, "Charlie", "3 anos", "Bulldog Francês", "Médio", "Sim", "", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "não | nao", "calmo", "cachorro64.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro64);

        Animal cachorro65 = new Cachorro(nextId++, "Lola", "2 anos", "Labrador", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "sim", "agitado", "cachorro65.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro65);

        Animal cachorro66 = new Cachorro(nextId++, "Coco", "1 ano", "Cocker Spaniel", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "media | medio | média | médio | grande", "sim", "calmo", "cachorro66.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro66);

        Animal cachorro67 = new Cachorro(nextId++, "Nina", "4 anos", "Shiba Inu", "Médio", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro67.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro67);

        Animal cachorro68 = new Cachorro(nextId++, "Toby", "5 anos", "Mastiff", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "sim", "calmo", "cachorro68.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro68);

        Animal cachorro69 = new Cachorro(nextId++, "Sophie", "3 anos", "Pitbull", "Médio", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro69.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro69);

        Animal cachorro70 = new Cachorro(nextId++, "Gizmo", "2 anos", "Beagle", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro70.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro70);

        Animal cachorro71 = new Cachorro(nextId++, "Luna", "6 anos", "Pastor Alemão", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "sim", "calmo", "cachorro71.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro71);

        Animal cachorro72 = new Cachorro(nextId++, "Oscar", "2 anos", "Basenji", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "agitado", "cachorro72.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro72);

        Animal cachorro73 = new Cachorro(nextId++, "Maggie", "3 anos", "Border Collie", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "media | medio | média | médio | grande", "sim", "agitado", "cachorro73.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro73);

        Animal cachorro74 = new Cachorro(nextId++, "Rufus", "4 anos", "Boxer", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "não | nao", "calmo", "cachorro74.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro74);

        Animal cachorro75 = new Cachorro(nextId++, "Bella", "1 ano", "São Bernardo", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro75.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro75);

        Animal cachorro76 = new Cachorro(nextId++, "Jack", "3 anos", "Chihuahua", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro76.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro76);

        Animal cachorro77 = new Cachorro(nextId++, "Zara", "2 anos", "Pastor Alemão", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "grande", "sim", "agitado", "cachorro77.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro77);

        Animal cachorro78 = new Cachorro(nextId++, "Finn", "4 anos", "Corgi", "Médio", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro78.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro78);

        Animal cachorro79 = new Cachorro(nextId++, "Chloe", "5 anos", "Vira Lata", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro79.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro79);

        Animal cachorro80 = new Cachorro(nextId++, "Leo", "1 ano", "Labrador", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "não | nao", "calmo", "cachorro80.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro80);

        Animal cachorro81 = new Cachorro(nextId++, "Misty", "3 anos", "Basset Hound", "Médio", "Sim", "", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro81.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro81);

        Animal cachorro82 = new Cachorro(nextId++, "Spike", "2 anos", "Chow Chow", "Grande", "Sim", "", "aventureiro | aventureira", "casa", "grande", "sim", "calmo", "cachorro82.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro82);

        Animal cachorro83 = new Cachorro(nextId++, "Ginger", "4 anos", "Boston Terrier", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "media | medio | média | médio | grande", "sim", "agitado", "cachorro83.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro83);

        Animal cachorro84 = new Cachorro(nextId++, "Rocky", "1 ano", "Pitbull", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "não | nao", "calmo", "cachorro84.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro84);

        Animal cachorro85 = new Cachorro(nextId++, "Peanut", "5 anos", "Yorkshire", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro85.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro85);

        Animal cachorro86 = new Cachorro(nextId++, "Sasha", "2 anos", "Beagle", "Médio", "Sim", "", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "não | nao", "agitado", "cachorro86.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro86);

        Animal cachorro87 = new Cachorro(nextId++, "Teddy", "3 anos", "Cocker Spaniel", "Médio", "Sim", "", "calmo | calma", "casa", "media | medio | média | médio | grande", "sim", "calmo", "cachorro87.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro87);

        Animal cachorro88 = new Cachorro(nextId++, "Nala", "4 anos", "Maltês", "Pequeno", "Sim", "", "calmo | calma", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "sim", "agitado", "cachorro88.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro88);

        Animal cachorro89 = new Cachorro(nextId++, "Bruno", "2 anos", "Doberman", "Grande", "Sim", "", "calmo | calma", "casa | apartamento", "grande", "sim", "agitado", "cachorro89.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro89);

        Animal cachorro90 = new Cachorro(nextId++, "Daisy", "1 ano", "Bichon Frise", "Pequeno", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro90.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro90);

        Animal cachorro91 = new Cachorro(nextId++, "Hugo", "3 anos", "São Bernardo", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro91.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro91);

        Animal cachorro92 = new Cachorro(nextId++, "Jasmine", "2 anos", "Cairn Terrier", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "pequena | pequeno | media | medio | média | médio | grande", "não | nao", "calmo", "cachorro92.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro92);

        Animal cachorro93 = new Cachorro(nextId++, "Maverick", "4 anos", "Golden Retriever", "Grande", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "calmo", "cachorro93.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro93);

        Animal cachorro94 = new Cachorro(nextId++, "Fiona", "1 ano", "Pitbull Terrier", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "não | nao", "agitado", "cachorro94.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro94);

        Animal cachorro95 = new Cachorro(nextId++, "Thor", "3 anos", "Akita Inu", "Grande", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "grande", "sim", "calmo", "cachorro95.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro95);

        Animal cachorro96 = new Cachorro(nextId++, "Kira", "2 anos", "Schnauzer", "Médio", "Sim", "", "brincalhao | brincalhão | brincalhonav", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro96.jpg", "Fêmea | Femea", true, "curta");
        cadastrarAnimal(cachorro96);

        Animal cachorro97 = new Cachorro(nextId++, "Rocky", "4 anos", "Beagle", "Médio", "Sim", "", "aventureiro | aventureira", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro97.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro97);

        Animal cachorro98 = new Cachorro(nextId++, "Chester", "1 ano", "Cocker Spaniel", "Médio", "Sim", "", "aventureiro | aventureira", "apartamento", "media | medio | média | médio | grande", "não | nao", "calmo", "cachorro98.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro98);

        Animal cachorro99 = new Cachorro(nextId++, "Stella", "5 anos", "Yorkshire Terrier", "Pequeno", "Sim", "", "aventureiro | aventureira", "casa", "pequena | pequeno | media | medio | média | médio | grande", "sim", "calmo", "cachorro99.jpg", "Fêmea | Femea", true, "longa");
        cadastrarAnimal(cachorro99);

        Animal cachorro100 = new Cachorro(nextId++, "Leo", "2 anos", "Pastor Alemão", "Grande", "Sim", "", "calmo | calma", "casa", "grande", "sim", "agitado", "cachorro100.jpg", "Macho", true, "curta");
        cadastrarAnimal(cachorro100);

        Animal cachorro101 = new Cachorro(nextId++, "Felpitcha", "5 anos", "Não definida", "Médio", "Sim", "", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro101.jpg", "Macho", false, "longa");
        cadastrarAnimal(cachorro101);

        Animal cachorro102 = new Cachorro(nextId++, "Ragen", "3 anos", "Não definida", "Médio", "Sim", "Castrado", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro102.jpg", "Macho", false, "curta");
        cadastrarAnimal(cachorro102);

        Animal cachorro103 = new Cachorro(nextId++, "Duddy", "5 anos", "Não definida", "Grande", "Sim", "Castrado", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "grande", "sim", "calmo", "cachorro103.jpg", "Macho", false, "curta");
        cadastrarAnimal(cachorro103);

        Animal cachorro104 = new Cachorro(nextId++, "Scooby", "10 anos", "Não definida", "Grande", "Sim", "Castrado", "aventureiro | aventureira", "casa | apartamento", "grande", "sim", "calmo", "cachorro104.jpg", "Macho", false, "curta");
        cadastrarAnimal(cachorro104);

        Animal cachorro105 = new Cachorro(nextId++, "Dianna", "4 anos", "Não definida", "Médio", "Sim", "Castrada", "brincalhao | brincalhão | brincalhona", "casa | apartamento", "media | medio | média | médio | grande", "sim", "agitado", "cachorro105.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro105);

        Animal cachorro106 = new Cachorro(nextId++, "Belly", "7 anos", "Não definida", "Grande", "Sim", "Castrada", "calmo | calma", "casa | apartamento", "grande", "sim", "calmo", "cachorro106.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro106);

        Animal cachorro107 = new Cachorro(nextId++, "Brownie", "5 anos", "Não definida", "Grande", "Sim", "Castrada", "aventureiro | aventureira", "casa | apartamento", "grande", "sim", "agitado", "cachorro107.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro107);

        Animal cachorro108 = new Cachorro(nextId++, "Penellope", "3 anos", "Não definida", "Médio", "Sim", "Castrada", "calmo | calma", "casa | apartamento", "media | medio | média | médio | grande", "sim", "calmo", "cachorro108.jpg", "Fêmea | Femea", false, "curta");
        cadastrarAnimal(cachorro108);

        Animal cachorro109 = new Cachorro(nextId++, "Dobby", "1 ano", "Não definida", "Médio", "Sim", "Castrado", "calmo | calma", "casa", "media | medio | média | médio | grande", "sim", "calmo", "cachorro109.jpg", "Macho", false, "curta");
        cadastrarAnimal(cachorro109);

        Animal cachorro110 = new Cachorro(nextId++, "Odin", "8 anos", "Border Collie", "Médio", "Sim", "Bravo", "brincalhao | brincalhão | brincalhona", "casa", "grande", "sim", "agitado", "cachorro110.jpg", "Macho", true, "longa");
        cadastrarAnimal(cachorro110);
    }
}








