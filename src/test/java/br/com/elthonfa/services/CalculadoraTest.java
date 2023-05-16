package br.com.elthonfa.services;

import br.com.elthonfa.exceptions.NaoPodeDividirPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

    private Calculadora calc;

    @Before
    public void setup() {
        calc = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores() {
        // cenário
        int primeiroNumero = 5;
        int segundoNumero = 3;

        // ação
        int resultado = calc.somar(primeiroNumero, segundoNumero);

        // verificação
        Assert.assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrairDoisValores() {
        // cenário
        int primeiroNumero = 5;
        int segundoNumero = 3;

        // ação
        int resultado = calc.subtrair(primeiroNumero, segundoNumero);

        // verificação
        Assert.assertEquals(2, resultado);
    }

    @Test
    public void deveMultiplicarDoisValores() {
        // cenário
        int primeiroNumero = 5;
        int segundoNumero = 3;

        // ação
        int resultado = calc.multiplicar(primeiroNumero, segundoNumero);

        // verificação
        Assert.assertEquals(15, resultado);
    }

    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
        // cenário
        double primeiroNumero = 5;
        double segundoNumero = 2;

        // ação
        double resultado = calc.dividir(primeiroNumero, segundoNumero);

        // verificação
        Assert.assertEquals(2.5, resultado, 0.01);
    }

    @Test(expected = NaoPodeDividirPorZeroException.class)
    public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
        // cenário
        double primeiroNumero = 10;
        double segundoNumero = 0;

        // ação
        calc.dividir(primeiroNumero, segundoNumero);
    }

    @Test
    public void deveDividirDoisNumerosPassandoStringComoParametros() {
        String a = "6";
        String b = "3";

        int resultado = calc.dividirComStringsComoParametros(a, b);

        Assert.assertEquals(2, resultado);
    }

}
