package br.pe.elthon.services;

import java.util.Date;

import br.pe.elthon.entities.Filme;
import br.pe.elthon.entities.Locacao;
import br.pe.elthon.entities.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static br.pe.elthon.utils.DataUtils.*;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		//Salvando a locacao...
		//TODO adicionar método para salvar

		return locacao;
	}

}