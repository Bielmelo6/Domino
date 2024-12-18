package Domino;

import java.util.ArrayList;
import java.util.List;

class AgenteBaseadoEmBusca implements Agente {

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        String[] pecas = mao.split(", ");
        for (int i = 0; i < pecas.length; i++) {
            pecas[i] = pecas[i].replace("<", "").replace(">", "");
        }

        if (!mesa.startsWith("[") || !mesa.endsWith("]")) {
            return -1;
        }
        String[] mesaPecas = mesa.substring(1, mesa.length() - 1).split(", ");
        String primeiraPeca = mesaPecas[0].replace("<", "").replace(">", "");
        String ultimaPeca = mesaPecas[mesaPecas.length - 1].replace("<", "").replace(">", "");

        char ladoEsquerdo = primeiraPeca.charAt(0);
        char ladoDireito = ultimaPeca.charAt(2);

        List<Integer> jogadasValidas = new ArrayList<>();

        for (int i = 0; i < pecas.length; i++) {
            String peca = pecas[i];
            char lado1 = peca.charAt(0);
            char lado2 = peca.charAt(2);

            if (lado1 == ladoEsquerdo || lado2 == ladoEsquerdo || lado1 == ladoDireito || lado2 == ladoDireito) {
                jogadasValidas.add(i);
            }
        }

        if (!jogadasValidas.isEmpty()) {
            return escolherMelhorJogada(jogadasValidas, pecas);
        }

        return -1;
    }

    private int escolherMelhorJogada(List<Integer> jogadasValidas, String[] pecas) {
        int melhorIndice = jogadasValidas.get(0);
        int maiorValor = Integer.MIN_VALUE;

        for (int indice : jogadasValidas) {
            String peca = pecas[indice];
            int valor = calcularValorPeca(peca);

            if (valor > maiorValor) {
                maiorValor = valor;
                melhorIndice = indice;
            }
        }

        return melhorIndice;
    }

    private int calcularValorPeca(String peca) {
        char lado1 = peca.charAt(0);
        char lado2 = peca.charAt(2);

        return Character.getNumericValue(lado1) + Character.getNumericValue(lado2);
    }
}
