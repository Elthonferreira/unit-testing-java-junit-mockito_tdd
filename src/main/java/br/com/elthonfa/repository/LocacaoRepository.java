package br.com.elthonfa.repository;

import br.com.elthonfa.entities.Locacao;

import java.util.List;

public interface LocacaoRepository {

    public void salvar(Locacao locacao);

    List<Locacao> obterLocacoesPendentes();
}
