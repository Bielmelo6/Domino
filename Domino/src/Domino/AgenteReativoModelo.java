package Domino;

import java.util.HashMap;
import java.util.Map;

class AgenteReativoModelo implements Agente {
    private Map<Integer, Integer> contagemPecas;

    public AgenteReativoModelo() {
        contagemPecas = new HashMap<>();
        for (int i = 0; i <= 6; i++) contagemPecas.put(i, 0);
    }

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        atualizarContagemPecas(mesa);

        String[] pecas = mao.split(", ");
        for (int i = 0; i < pecas.length; i++) {
            pecas[i] = pecas[i].replace("<", "").replace(">", "");
        }

        String[] mesaPecas = mesa.substring(1, mesa.length() - 1).split(", ");
        String primeiraPeca = mesaPecas[0].replace("<", "").replace(">", "");
        String ultimaPeca = mesaPecas[mesaPecas.length - 1].replace("<", "").replace(">", "");

        char ladoEsquerdo = primeiraPeca.charAt(0);
        char ladoDireito = ultimaPeca.charAt(2);

        int melhorIndice = -1;
        int maiorPrioridade = Integer.MIN_VALUE;

        for (int i = 0; i < pecas.length; i++) {
            String peca = pecas[i];
            char lado1 = peca.charAt(0);
            char lado2 = peca.charAt(2);

            if (lado1 == ladoEsquerdo || lado2 == ladoEsquerdo || lado1 == ladoDireito || lado2 == ladoDireito) {
                int prioridade = calcularPrioridade(lado1, lado2);
                if (prioridade > maiorPrioridade) {
                    maiorPrioridade = prioridade;
                    melhorIndice = i;
                }
            }
        }

        return melhorIndice;
    }

    private void atualizarContagemPecas(String mesa) {
        String[] pecasMesa = mesa.substring(1, mesa.length() - 1).split(", ");
        for (String peca : pecasMesa) {
            peca = peca.replace("<", "").replace(">", "");
            char lado1 = peca.charAt(0);
            char lado2 = peca.charAt(2);

            contagemPecas.put(Character.getNumericValue(lado1),
                    contagemPecas.get(Character.getNumericValue(lado1)) + 1);
            contagemPecas.put(Character.getNumericValue(lado2),
                    contagemPecas.get(Character.getNumericValue(lado2)) + 1);
        }
    }

    private int calcularPrioridade(char lado1, char lado2) {
        return contagemPecas.get(Character.getNumericValue(lado1)) + contagemPecas.get(Character.getNumericValue(lado2));
    }
}
