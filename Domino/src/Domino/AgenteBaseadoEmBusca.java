package Domino;

class AgenteBaseadoEmBusca implements Agente {

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        // Extrai os números extremos da mesa
        char ladoEsquerdo = mesa.charAt(2); // Lado esquerdo
        char ladoDireito = mesa.charAt(mesa.length() - 3); // Lado direito

        // Divide as peças da mão
        String[] pecas = mao.split("<");

        // Itera sobre as peças na mão para encontrar um encaixe
        for (int i = 1; i < pecas.length; i++) {
            char lado1 = pecas[i].charAt(0);
            char lado2 = pecas[i].charAt(2);

            // Verifica se a peça encaixa em qualquer lado da mesa
            if (lado1 == ladoEsquerdo || lado2 == ladoEsquerdo ||
                    lado1 == ladoDireito || lado2 == ladoDireito) {
                return i - 1; // Retorna o índice da peça válida
            }
        }

        // Retorna índice inválido se nenhuma peça for jogável
        return -1;
    }
}