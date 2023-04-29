package br.com.elthonfa.services;

import java.util.Date;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.exceptions.FilmeSemEstoqueException;
import br.com.elthonfa.exceptions.LocadoraException;
import br.com.elthonfa.utils.DataUtils;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws Exception {
		if (filme.getEstoque() == 0) {
			throw new FilmeSemEstoqueException();
		}

		if (usuario == null) {
			throw new LocadoraException("Usuario nao existente.");
		}

		if (filme == null) {
			throw new LocadoraException("Filme nao existente.");
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

}