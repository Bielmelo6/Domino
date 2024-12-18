package Domino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Domino {

    List<Peca> monte = new ArrayList<>();
    List<Peca> mesa = new ArrayList<>();
    Jogador jogador1, jogador2;

    public Domino(Jogador nome1, Jogador nome2) {
        jogador1 = nome1;
        jogador2 = nome2;
    }

    private void inicializarMonte() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                monte.add(new Peca(i, j));
            }
        }
        Collections.shuffle(monte);
    }

    private void distribuirPecas() {
        for (int i = 0; i < 7; i++) {
            jogador1.comprarPeca(monte.remove(0));
            jogador2.comprarPeca(monte.remove(0));
        }
    }

    private void mostrarMesa() {
        System.out.println("Mesa: " + mesa);
    }

    public int iniciarJogo(long miliSegundosDeIntervalo, int maximoDeJogadasInvalidas) {

        jogador1.start();
        jogador2.start();

        inicializarMonte();
        distribuirPecas();

        mesa.add(monte.remove(0)); // Coloca a primeira peça na mesa

        boolean vezJogador1 = true;
        int jogadasInvalidas = 0;

        while (!jogador1.mao.isEmpty() && !jogador2.mao.isEmpty() && jogadasInvalidas < maximoDeJogadasInvalidas) {

            mostrarMesa();

            Jogador jogadorAtual = vezJogador1 ? jogador1 : jogador2;
            System.out.println(jogadorAtual.nome + ", é sua vez!");
            jogadorAtual.mostrarMao();
            jogadorAtual.setMesa(mesa.toString());

            synchronized (jogadorAtual) {
                jogadorAtual.notify();
            }

            try {
                Thread.sleep(miliSegundosDeIntervalo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Peca pecaEscolhida = jogadorAtual.peca_escolhida;

            // Verifica se a peça encaixa em algum lado da mesa
            Peca primeiraPecaMesa = mesa.get(0);
            Peca ultimaPecaMesa = mesa.get(mesa.size() - 1);

            if (pecaEscolhida.encaixa(primeiraPecaMesa.lado1)) {
                if (pecaEscolhida.lado2 != primeiraPecaMesa.lado1) {
                    pecaEscolhida.inverter();
                }
                mesa.add(0, pecaEscolhida); // Adiciona no início da mesa
            } else if (pecaEscolhida.encaixa(ultimaPecaMesa.lado2)) {
                if (pecaEscolhida.lado1 != ultimaPecaMesa.lado2) {
                    pecaEscolhida.inverter();
                }
                mesa.add(pecaEscolhida); // Adiciona no final da mesa
            } else {
                System.out.println("Peça inválida! Perdeu a vez.");
                jogadorAtual.comprarPeca(pecaEscolhida); // Devolve a peça se não puder jogar
                jogadasInvalidas++;
            }

            vezJogador1 = !vezJogador1; // Troca de jogador
        }

        if (jogador1.mao.isEmpty()) {
            System.out.println("Parabéns " + jogador1.nome + ", você venceu!");
            return 1;
        } else if (jogador2.mao.isEmpty()) {
            System.out.println("Parabéns " + jogador2.nome + ", você venceu!");
            return 2;
        } else if (jogador1.pontos() < jogador2.pontos()) {
            System.out.println("Parabéns " + jogador1.nome + ", você venceu!");
            return 1;
        } else if (jogador1.pontos() > jogador2.pontos()) {
            System.out.println("Parabéns " + jogador2.nome + ", você venceu!");
            return 2;
        }
        System.out.println("O jogo terminou empatado.");
        return -1;

    }

    public static void main(String[] args) {
        Jogador jog1 = new Jogador("CONSTANTE", new AgenteConstante());
        System.out.println("Nome do Domino.Jogador 1: " + jog1.nome);
        Jogador jog2 = new Jogador("RANDÔMICO", new AgenteRandomicoTotal());
        System.out.println("Nome do Domino.Jogador 2: " + jog2.nome);
        Jogador jog3 = new Jogador("SIMPLES-1", new AgenteSimples());
        System.out.println("Nome do Domino.Jogador 3: " + jog3.nome);
        Jogador jog4 = new Jogador("SIMPLES-2", new AgenteSimples());
        System.out.println("Nome do Domino.Jogador 4: " + jog4.nome);
        Jogador jog5 = new Jogador("BUSCA", new AgenteBaseadoEmBusca());
        System.out.println("Nome do Domino.Jogador 4: " + jog5.nome);
        Jogador jog6 = new Jogador("REATIVO-MODELO", new AgenteReativoModelo());
        System.out.println("Nome do Domino.Jogador 4: " + jog6.nome);

        Jogador jogador1 = jog5;
        Jogador jogador2 = jog6;
        Domino jogo = new Domino(jogador1, jogador2);

        long miliSegundosDeIntervaloPorJogador = 100;
        int maximoDeJogadasInvalidas = 4;
        int saida = jogo.iniciarJogo(miliSegundosDeIntervaloPorJogador, maximoDeJogadasInvalidas);
        System.out.println(saida);
        System.exit(0);
    }

}