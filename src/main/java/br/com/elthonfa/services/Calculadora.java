package br.com.elthonfa.services;

import br.com.elthonfa.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {


    public int somar(int primeiroNumero, int segundoNumero) {
        return primeiroNumero + segundoNumero;
    }

    public int subtrair(int primeiroNumero, int segundoNumero) {
        return primeiroNumero - segundoNumero;
    }

    public int multiplicar(int primeiroNumero, int segundoNumero) {
        return primeiroNumero * segundoNumero;
    }

    public double dividir(double primeiroNumero, double segundoNumero) throws NaoPodeDividirPorZeroException {
        if (segundoNumero == 0) {
            throw new NaoPodeDividirPorZeroException();
        }

        return primeiroNumero / segundoNumero;
    }

    public int dividirComStringsComoParametros(String a, String b) {
        return Integer.valueOf(a) / Integer.valueOf(b);
    }
}
