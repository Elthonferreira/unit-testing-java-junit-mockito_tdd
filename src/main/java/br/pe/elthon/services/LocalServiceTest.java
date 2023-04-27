package br.pe.elthon.services;

import br.pe.elthon.entities.Filme;
import br.pe.elthon.entities.Locacao;
import br.pe.elthon.entities.Usuario;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static br.pe.elthon.utils.DataUtils.adicionarDias;
import static br.pe.elthon.utils.DataUtils.isMesmaData;

public class LocalServiceTest {

    @Test
    public void teste () {
        // Cenário
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Elthon Ferreira");
        Filme filme = new Filme("Interestelar", 500, 49.99D);
        // Ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);

        // Verificação
        Assert.assertTrue(locacao.getUsuario().getNome().equals("Elthon Ferreira"));
        Assert.assertTrue(locacao.getFilme().getNome().equals("Interestelar"));
        Assert.assertTrue(locacao.getValor().equals(49.99D));
        Assert.assertTrue(isMesmaData(locacao.getDataLocacao(), new Date()));
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(), adicionarDias(locacao.getDataLocacao(), 1)));
    }
}
