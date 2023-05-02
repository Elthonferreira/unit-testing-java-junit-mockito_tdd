package br.com.elthonfa.services;

import java.util.Date;
import java.util.List;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.exceptions.FilmeSemEstoqueException;
import br.com.elthonfa.exceptions.LocadoraException;
import br.com.elthonfa.utils.DataUtils;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;

public class LocacaoService {

	public Double somatorioValorDosFilmes(List<Filme> filmes) {
		Double somatorio = 0D;

		for (Filme filme : filmes) {
			somatorio += filme.getPrecoLocacao();
		}

		return somatorio;
	}

	public Locacao alugarFilmes(Usuario usuario, List<Filme> filmes) throws Exception {
		if (usuario == null) {
			throw new LocadoraException("Usuario nao existente.");
		}

		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme(s) nao existente.");
		}

		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}


		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(somatorioValorDosFilmes(filmes));

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

}