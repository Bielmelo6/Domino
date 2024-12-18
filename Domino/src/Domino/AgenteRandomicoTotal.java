package Domino;

import java.util.Random;

class AgenteRandomicoTotal implements Agente {

    static final Random rand = new Random();

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        String[] maos = mao.split("<");
        return rand.nextInt(maos.length - 1);
    }

}