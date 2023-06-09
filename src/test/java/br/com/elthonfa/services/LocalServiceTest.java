package br.com.elthonfa.services;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;
import br.com.elthonfa.exceptions.LocadoraException;
import br.com.elthonfa.repository.LocacaoRepository;
import br.com.elthonfa.utils.DataUtils;
import builders.FilmeBuilder;
import builders.LocacaoBuilder;
import builders.UsuarioBuilder;
import matchers.DiaSemanaMatcher;
import matchers.MatchersProprios;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.mockito.Mockito;

import java.util.*;

public class LocalServiceTest {

    private LocacaoService locacaoService;
    private SPCService spcService;
    private LocacaoRepository locacaoRepository;
    private EmailService emailService;
    @Before
    public void funcaoQueIniciaANTESDeCadaTeste() {
        locacaoRepository = Mockito.mock(LocacaoRepository.class);
        spcService = Mockito.mock(SPCService.class);
        emailService = Mockito.mock(EmailService.class);

        locacaoService = new LocacaoService();
        locacaoService.setLocacaoRepository(locacaoRepository);
        locacaoService.setSPCService(spcService);
        locacaoService.setEmailService(emailService);
    }

    @Test
    public void deveAlugarFilme () throws Exception {
        Assume.assumeFalse(DataUtils.isMesmoDiaDaSemana(new Date(), Calendar.SATURDAY));

        // Cenário
        Usuario usuario = UsuarioBuilder.umUsuario();
        List<Filme> filmes = Arrays.asList(new Filme("Interestelar", 2, 49.99D), new Filme("Ilha do medo", 2, 39.99D));

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        Assert.assertEquals(locacao.getUsuario().getNome(),"Elthon Ferreira");
        Assert.assertEquals(locacao.getFilmes().get(0).getNome(), "Interestelar");
        Assert.assertEquals(locacao.getFilmes().get(1).getNome(), "Ilha do medo");
        Assert.assertEquals(locacao.getValor(), 85.981, 0.01);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.adicionarDias(locacao.getDataLocacao(), 1)));

        // Verificaçao com assertThat
        Assert.assertThat(locacao.getUsuario().getNome(), CoreMatchers.is("Elthon Ferreira"));
        Assert.assertThat(locacao.getValor(), CoreMatchers.is(85.981));
        Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
        Assert.assertThat(locacao.getDataLocacao(), MatchersProprios.ehHoje());
        Assert.assertThat(locacao.getDataRetorno(), MatchersProprios.ehHojeComDiferencaDias(1));
    }

    @Test(expected = Exception.class)
    public void naoDeveAlugarFilmeSemEstoque() throws Exception {
        // Cenário
        Usuario usuario = UsuarioBuilder.umUsuario();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilmeSemEstoque());

        // Ação
        locacaoService.alugarFilmes(usuario, filmes);
    }

    @Test(expected = LocadoraException.class)
    public void naoDeveAlugarFilmeSemUsuario() throws Exception {
        List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme());

        Locacao locacao = locacaoService.alugarFilmes(null, filmes);
    }

    @Test(expected = LocadoraException.class)
    public void naoDeveAlugarFilmeSemFilme() throws Exception {
        Usuario usuario = UsuarioBuilder.umUsuario();
        Locacao locacao = locacaoService.alugarFilmes(usuario, null);
    }

    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws Exception {
        Assume.assumeTrue(DataUtils.isMesmoDiaDaSemana(new Date(), Calendar.SATURDAY));

        // Cenário
        Usuario usuario = UsuarioBuilder.umUsuario();

        List<Filme> filmes = new ArrayList<>();
        Filme filme = FilmeBuilder.umFilme();
        filmes.add(filme);

        // Ação
        Locacao locacao = locacaoService.alugarFilmes(usuario, filmes);

        // Verificação
        boolean isSegunda = DataUtils.isMesmoDiaDaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(isSegunda);
        Assert.assertThat(locacao.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
    }

    @Test(expected = LocadoraException.class)
    public void naoDeveAlugarFilmeParaNegativadoSPC() throws Exception {
        // Cenário
        Usuario usuario = UsuarioBuilder.umUsuario();
        Usuario usuarioComNomeCompleto = UsuarioBuilder.umUsuarioComNomeCompleto(); // caso de erro
        List<Filme> filmes = new ArrayList<>();
        Filme filme = FilmeBuilder.umFilme();
        filmes.add(filme);

        Mockito.when(spcService.possuiNegativacao(usuario)).thenReturn(true);

        locacaoService.alugarFilmes(usuario, filmes);

        Mockito.verify(spcService).possuiNegativacao(usuario);
    }

    @Test
    public void deveEnviarEmailParaLocacoesAtrasadas() {
        Usuario usuario = UsuarioBuilder.umUsuario();
        Usuario usuarioComNomeCompleto = UsuarioBuilder.umUsuarioComNomeCompleto(); // caso de erro

        List<Locacao> locacoes = Arrays.asList(LocacaoBuilder.umLocacao().comUsuario(usuario).
                comDataRetorno(DataUtils.obterDataComDiferencaDias(-2)).agora());

        Mockito.when(locacaoRepository.obterLocacoesPendentes()).thenReturn(locacoes);

        locacaoService.notificarAtrasos();

        Mockito.verify(emailService).notificarAtraso(usuario);
    }
}
