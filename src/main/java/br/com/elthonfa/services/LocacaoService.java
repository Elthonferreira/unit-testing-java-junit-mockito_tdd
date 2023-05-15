package br.com.elthonfa.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.exceptions.FilmeSemEstoqueException;
import br.com.elthonfa.exceptions.LocadoraException;
import br.com.elthonfa.utils.DataUtils;
import br.com.elthonfa.entities.Locacao;
import br.com.elthonfa.entities.Usuario;
import buildermaster.BuilderMaster;

public class LocacaoService {

    private Double aplicarDesconto(Filme filme, int contador) {
        if (contador == 1) return filme.getPrecoLocacao() * 0.90;

        if (contador == 2) return filme.getPrecoLocacao() * 0.75;

        if (contador == 3) return filme.getPrecoLocacao() * 0.50;

        if (contador == 4) return filme.getPrecoLocacao() * 0.25;

        if (contador == 5) return 0D;

        return null;
    }

    public Double somatorioValorDosFilmes(List<Filme> filmes) {
        Double somatorio = 0D;

        for (int i = 0; i < filmes.size(); i++) {
            double precoFilme = filmes.get(i).getPrecoLocacao();

            if (i >= 1 && i <= 5) {
                precoFilme = aplicarDesconto(filmes.get(i), i);
            }

            somatorio += precoFilme;
        }

        return somatorio;
    }


    public void verificacoesDeErrosDaLocacao(Usuario usuario, List<Filme> filmes) throws
            LocadoraException, FilmeSemEstoqueException {
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
    }

    public Locacao alugarFilmes(Usuario usuario, List<Filme> filmes) throws Exception {
        verificacoesDeErrosDaLocacao(usuario, filmes);

        Locacao locacao = new Locacao();
        locacao.setFilmes(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(somatorioValorDosFilmes(filmes));

        Date dataEntrega = new Date();
        dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);

        if (isDomingo(dataEntrega)) {
            dataEntrega = DataUtils.adicionarDias(dataEntrega, 1);
        }
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    private static boolean isDomingo(Date dataEntrega) {
        return DataUtils.isMesmoDiaDaSemana(dataEntrega, Calendar.SUNDAY);
    }

    public static void main(String[] args) {
        new BuilderMaster().gerarCodigoClasse(Locacao.class);
    }

}