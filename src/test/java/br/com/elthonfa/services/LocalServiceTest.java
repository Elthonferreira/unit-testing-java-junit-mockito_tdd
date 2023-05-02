package br.com.elthonfa.services;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;
import br.com.elthonfa.exceptions.LocadoraException;
import br.com.elthonfa.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.Date;

public class LocalServiceTest {

    private LocacaoService locacaoService;
    @Before
    public void funcaoQueIniciaANTESDeCadaTeste() {
        locacaoService = new LocacaoService();
    }

    @Test
    public void testeLocacao () throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        Filme filme = new Filme("Interestelar", 2, 49.99D);

        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);

        // Verificação
        Assert.assertEquals(locacao.getUsuario().getNome(),"Elthon Ferreira");
        Assert.assertEquals(locacao.getFilme().getNome(), "Interestelar");
        Assert.assertEquals(locacao.getValor(), 49.99, 0.01);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.adicionarDias(locacao.getDataLocacao(), 1)));

        // Verificaçao com assertThat
        Assert.assertThat(locacao.getUsuario().getNome(), CoreMatchers.is("Elthon Ferreira"));
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(49.99));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));

    }

    @Test(expected = Exception.class)
    public void testeLocacaoFilmeSemEstoque() throws Exception {
        // Cenário
        Usuario usuario = new Usuario("Elthon Ferreira");
        Filme filme = new Filme("Interestelar", 0, 49.99D);

        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);
    }

    @Test(expected = LocadoraException.class)
    public void testeLocacaoUsuarioVazio() throws Exception {
        Filme filme = new Filme("Interestelar", 5, 49.99D);

        Locacao locacao = locacaoService.alugarFilme(null, filme);
    }

    @Test(expected = LocadoraException.class)
    public void testeLocacaoFilmeVazio() throws Exception {
        Usuario usuario = new Usuario("Elthon");

        Locacao locacao = locacaoService.alugarFilme(usuario, null);
    }


}
