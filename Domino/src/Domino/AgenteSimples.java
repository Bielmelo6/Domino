package Domino;

import java.util.Random;

class AgenteSimples implements Agente {

    static final Random rand = new Random();

    @Override
    public int indicePecaParaJogar(String mesa, String mao) {
        // duas opções de números possíveis para jogar
        char op1 = mesa.charAt(2);
        char op2 = mesa.charAt(mesa.length() - 3);
        // System.out.println(op1 + " ou " + op2 );
        String[] maos = mao.split("<"); // todas as peças
        int indice = 0;
        System.out.println();
        for (int i = 1; i < maos.length; i++) {
//			System.out.print(maos[i].charAt(0) + ", " + maos[i].charAt(2) + ", ");
//			System.out.println("--" + maos[i] + "--");
            if (maos[i].charAt(0) == op1)
                return i - 1; // a peça encaixa do lado esquerdo com a op1
            if (maos[i].charAt(2) == op1)
                return i - 1;// a peça encaixa do lado direito com a op1
            if (maos[i].charAt(0) == op2)
                return i - 1;// a peça encaixa do lado esquerdo com a op2
            if (maos[i].charAt(2) == op2)
                return i - 1;// a peça encaixa do lado direito com a op2
        }
        System.out.println();
        return indice;
    }

}