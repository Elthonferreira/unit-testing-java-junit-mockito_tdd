package br.com.elthonfa.services;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;
import br.com.elthonfa.exceptions.LocadoraException;
import br.com.elthonfa.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.*;

public class LocalServiceTest {

    private LocacaoService locacaoService;
    @Before
    public void funcaoQueIniciaANTESDeCadaTeste() {
        locacaoService = new LocacaoService();
    }

    @Test
    public void deveAlugarFilme () throws Exception {
        Assume.assumeFalse(DataUtils.isMesmoDiaDaSemana(new Date(), Calendar.SATURDAY));

        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        List<Filme> filmes = Arrays.asList(new Filme("Interestelar", 2, 49.99D), new Filme("Ilha do medo", 2, 39.99D));

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getUsuario().getNome(),"Elthon Ferreira");
        Assert.assertEquals(locacao.getFilmes().get(0).getNome(), "Interestelar");
        Assert.assertEquals(locacao.getFilmes().get(1).getNome(), "Ilha do medo");
        Assert.assertEquals(locacao.getValor(), 89.98, 0.01);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.adicionarDias(locacao.getDataLocacao(), 1)));

        // Verificaçao com assertThat
        Assert.assertThat(locacao.getUsuario().getNome(), CoreMatchers.is("Elthon Ferreira"));
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(89.98));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));

    }

    @Test(expected = Exception.class)
    public void naoDeveAlugarFilmeSemEstoque() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        List<Filme> filmes = Arrays.asList(new Filme("Interestelar", 0, 49.99D));

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);
    }

    @Test(expected = LocadoraException.class)
    public void naoDeveAlugarFilmeSemUsuario() throws Exception {
        List<Filme> filmes = Arrays.asList(new Filme("Interestelar", 0, 49.99D));

        Locacao locacao = locacaoService.alugarFilmes(null, filmes);
    }

    @Test(expected = LocadoraException.class)
    public void naoDeveAlugarFilmeSemFilme() throws Exception {
        Usuario usuario = new Usuario("Elthon");

        Locacao locacao = locacaoService.alugarFilmes(usuario, null);
    }

    @Test
    public void deveTerUmDescontoDe25PorCentoNoTerceiroFilme() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        List<Filme> filmes = new ArrayList<>();
        Filme filme1 = new Filme("Interestelar", 10, 100D);
        Filme filme2 = new Filme("Ilha do medo", 10, 100D);
        Filme filme3 = new Filme("Joker", 10, 100D);

        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getValor(), 275, 0.01);
    }

    @Test
    public void deveTerUmDescontoDe50PorCentoNoQuartoFilme() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        List<Filme> filmes = new ArrayList<>();
        Filme filme1 = new Filme("Interestelar", 10, 100D);
        Filme filme2 = new Filme("Ilha do medo", 10, 100D);
        Filme filme3 = new Filme("Joker", 10, 100D);
        Filme filme4 = new Filme("Harry Potter", 10, 100D);

        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getValor(), 325, 0.01);
    }

    @Test
    public void deveTerUmDescontoDe75PorCentoNoQuintoFilme() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        List<Filme> filmes = new ArrayList<>();
        Filme filme1 = new Filme("Interestelar", 10, 100D);
        Filme filme2 = new Filme("Ilha do medo", 10, 100D);
        Filme filme3 = new Filme("Joker", 10, 100D);
        Filme filme4 = new Filme("Harry Potter", 10, 100D);
        Filme filme5 = new Filme("O Predestinado", 10, 100D);

        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);
        filmes.add(filme5);

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getValor(), 350, 0.01);
    }

    @Test
    public void deveTerUmDescontoDe100PorCentoNoSextoFilme() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        List<Filme> filmes = new ArrayList<>();
        Filme filme1 = new Filme("Interestelar", 10, 100D);
        Filme filme2 = new Filme("Ilha do medo", 10, 100D);
        Filme filme3 = new Filme("Joker", 10, 100D);
        Filme filme4 = new Filme("Harry Potter", 10, 100D);
        Filme filme5 = new Filme("O Predestinado", 10, 100D);
        Filme filme6 = new Filme("Seven", 10, 100D);

        filmes.add(filme1);
        filmes.add(filme2);
        filmes.add(filme3);
        filmes.add(filme4);
        filmes.add(filme5);
        filmes.add(filme6);

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getValor(), 350, 0.01);
    }

    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws Exception {
        Assume.assumeTrue(DataUtils.isMesmoDiaDaSemana(new Date(), Calendar.SATURDAY));

        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");

        List<Filme> filmes = new ArrayList<>();
        Filme filme = new Filme("Interestelar", 10, 100D);
        filmes.add(filme);

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        boolean isSegunda = DataUtils.isMesmoDiaDaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(isSegunda);
    }
}
