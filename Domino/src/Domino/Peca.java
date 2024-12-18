package Domino;

class Peca {
    int lado1, lado2;

    public Peca(int lado1, int lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    @Override
    public String toString() {
        return "<" + lado1 + "." + lado2 + ">";
    }

    public boolean encaixa(int valor) {
        return lado1 == valor || lado2 == valor;
    }

    public void inverter() {
        int temp = lado1;
        lado1 = lado2;
        lado2 = temp;
    }
}