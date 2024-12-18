package Domino;

import java.util.HashMap;
import java.util.Map;

class AgenteReativoModelo implements Agente {

    private Map<Integer, Integer> contagemPecas; // Contador de aparições dos números 0-6
    private String ultimoEstadoMesa; // Estado da mesa na última jogada

    public AgenteReativoModelo() {
        contagemPecas = new HashMap<>();
        for (int i = 0; i <= 6; i++) {
            contagemPecas.put(i, 0); // Inicializa a contagem das peças
        }
        ultimoEstadoMesa = "";
    }

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        atualizarContagemPecas(mesa);

        // Obtém as extremidades atuais da mesa
        char ladoEsquerdo = mesa.charAt(2);
        char ladoDireito = mesa.charAt(mesa.length() - 3);

        String[] pecas = mao.split("<");
        int melhorIndice = -1;
        int menorContagem = Integer.MAX_VALUE;

        // Busca pela peça mais estratégica com base nas contagens
        for (int i = 1; i < pecas.length; i++) {
            char lado1 = pecas[i].charAt(0);
            char lado2 = pecas[i].charAt(2);

            // Verifica se encaixa
            if (lado1 == ladoEsquerdo || lado2 == ladoEsquerdo || lado1 == ladoDireito || lado2 == ladoDireito) {
                int contagemAtual = contagemPecas.get(Character.getNumericValue(lado1)) +
                        contagemPecas.get(Character.getNumericValue(lado2));

                // Escolhe a peça com os valores menos comuns (mais valiosa no futuro)
                if (contagemAtual < menorContagem) {
                    menorContagem = contagemAtual;
                    melhorIndice = i - 1;
                }
            }
        }

        return melhorIndice; // Retorna a peça mais estratégica
    }

    // Atualiza o histórico e a contagem das peças a partir do estado da mesa
    private void atualizarContagemPecas(String mesa) {
        if (mesa.equals(ultimoEstadoMesa)) return; // Evita processamento desnecessário

        String[] pecasMesa = mesa.split("<");

        for (int i = 1; i < pecasMesa.length; i++) {
            char lado1 = pecasMesa[i].charAt(0);
            char lado2 = pecasMesa[i].charAt(2);

            contagemPecas.put(Character.getNumericValue(lado1),
                    contagemPecas.get(Character.getNumericValue(lado1)) + 1);
            contagemPecas.put(Character.getNumericValue(lado2),
                    contagemPecas.get(Character.getNumericValue(lado2)) + 1);
        }

        ultimoEstadoMesa = mesa; // Atualiza o estado da mesa
    }
}