package br.com.elthonfa.services;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    private LocacaoService locacaoService;

    @Parameterized.Parameter(value = 0) // primeiro parâmetro do @Parameters
    public List<Filme> filmes;

    @Parameterized.Parameter(value = 1) // segundo parâmetro do @Parameters
    public Double valorLocacao;

    @Parameterized.Parameter(value = 2) // terceiro parâmetro do @Parameters
    public String cenario;


    @Before
    public void setup() {
        locacaoService = new LocacaoService();
    }

    private static Filme filme1 = new Filme("Interstellar", 10, 100D);
    private static Filme filme2 = new Filme("Shutter Island", 10, 100D);
    private static Filme filme3 = new Filme("Joker", 10, 100D);
    private static Filme filme4 = new Filme("Harry Potter", 10, 100D);
    private static Filme filme5 = new Filme("Predestination", 10, 100D);
    private static Filme filme6 = new Filme("Seven", 10, 100D);
    private static Filme filme7 = new Filme("Catch me if you can", 10, 100D);

    @Parameterized.Parameters(name = "{2}") // {2} é o terceiro parâmetro
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList(filme1, filme2), 190D, "2 Filmes: deve conceder 10% de desconto no segundo filme"},
                {Arrays.asList(filme1, filme2, filme3), 265D, "3 Filmes: deve conceder 25% de desconto no terceiro filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 315D, "4 Filmes: deve conceder 50% de desconto no quarto filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 340D, "5 Filmes: deve conceder 75% de desconto no quinto filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 340D, "6 Filmes: deve conceder 100% de desconto no sexto filme"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 440D, "7 Filmes: Sem descontos"},
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getValor(), valorLocacao, 0.01);
    }
}
