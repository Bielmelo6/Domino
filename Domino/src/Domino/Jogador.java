package Domino;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
class Jogador extends Thread {
    String nome;
    List<Peca> mao = new ArrayList<>();
    Agente agente;
    Peca peca_escolhida;
    String mesa;

    public Jogador(String nome, Agente agente) {
        this.nome = nome;
        this.agente = agente;
    }

    public synchronized int pontos() {
        int p = 0;
        for (Iterator<Peca> iterator = mao.iterator(); iterator.hasNext();) {
            Peca peca = (Peca) iterator.next();
            p += peca.lado1;
            p += peca.lado2;
        }
        return p;
    }

    public synchronized void comprarPeca(Peca peca) {
        mao.add(peca);
    }

    public synchronized void mostrarMao() {
        System.out.println("Mão de " + nome + ": " + mao);
    }

    public synchronized void jogarPeca() {
        int indice = agente.indicePecaParaJogar(mesa, mao.toString());
        if (indice < 0)
            indice = 0;
        if (indice >= mao.size())
            indice = 0;
        peca_escolhida = mao.remove(indice);
    }

    public synchronized void setMesa(String mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    this.wait(); // Aguarda até que algum agendamento ocorra.
                }
            } catch (InterruptedException e) {
                System.out.println(this.getName() + ": Erro no Controlador");
            }

            jogarPeca();

        }
    }
}