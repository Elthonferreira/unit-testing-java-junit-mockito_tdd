package br.com.elthonfa.services;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;
import br.com.elthonfa.repository.LocacaoRepository;
import builders.FilmeBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

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
        LocacaoRepository locacaoRepository = Mockito.mock(LocacaoRepository.class);
        locacaoService.setLocacaoRepository(locacaoRepository);
    }

    @Parameterized.Parameters(name = "{2}") // {2} é o terceiro parâmetro
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][] {
                {FilmeBuilder.criarNFilmes(2), 190D, "2 Filmes: deve conceder 10% de desconto no segundo filme"},
                {FilmeBuilder.criarNFilmes(3), 265D, "3 Filmes: deve conceder 25% de desconto no terceiro filme"},
                {FilmeBuilder.criarNFilmes(4), 315D, "4 Filmes: deve conceder 50% de desconto no quarto filme"},
                {FilmeBuilder.criarNFilmes(5), 340D, "5 Filmes: deve conceder 75% de desconto no quinto filme"},
                {FilmeBuilder.criarNFilmes(6), 340D, "6 Filmes: deve conceder 100% de desconto no sexto filme"},
                {FilmeBuilder.criarNFilmes(7), 440D, "7 Filmes: Sem descontos"},
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
