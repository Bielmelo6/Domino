package Domino;

import java.util.HashMap;
import java.util.Map;

class AgenteReativoModelo implements Agente {
    private Map<Integer, Integer> contagemPecas; // Histórico simplificado de peças

    public AgenteReativoModelo() {
        contagemPecas = new HashMap<>();
        for (int i = 0; i <= 6; i++) contagemPecas.put(i, 0);
    }

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        atualizarContagemPecas(mesa);

        String[] pecas = mao.split("<");
        char ladoEsquerdo = mesa.charAt(2);
        char ladoDireito = mesa.charAt(mesa.length() - 3);

        for (int i = 1; i < pecas.length; i++) {
            char lado1 = pecas[i].charAt(0);
            char lado2 = pecas[i].charAt(2);

            if (lado1 == ladoEsquerdo || lado2 == ladoEsquerdo || lado1 == ladoDireito || lado2 == ladoDireito) {
                return i - 1;
            }
        }

        return -1;
    }

    private void atualizarContagemPecas(String mesa) {
        String[] pecasMesa = mesa.split("<");
        for (int i = 1; i < pecasMesa.length; i++) {
            char lado1 = pecasMesa[i].charAt(0);
            char lado2 = pecasMesa[i].charAt(2);

            contagemPecas.put(Character.getNumericValue(lado1),
                    contagemPecas.get(Character.getNumericValue(lado1)) + 1);
            contagemPecas.put(Character.getNumericValue(lado2),
                    contagemPecas.get(Character.getNumericValue(lado2)) + 1);
        }
    }
}