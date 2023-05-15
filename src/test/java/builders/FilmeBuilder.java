package builders;

import br.com.elthonfa.entities.Filme;
import br.com.elthonfa.utils.Movie;

import java.util.ArrayList;
import java.util.List;

public class FilmeBuilder {

    private FilmeBuilder() {}

    public static Filme umFilme() {
        Filme filme = new Filme();
        filme.setNome("Interstellar");
        filme.setEstoque(10);
        filme.setPrecoLocacao(100D);

        return filme;
    }

    public static Filme umFilmeSemEstoque() {
        Filme filme = new Filme();
        filme.setNome("Interstellar");
        filme.setEstoque(0);
        filme.setPrecoLocacao(100D);
        return filme;
    }

    public static List<Filme> criarNFilmes(int quantidadeDeFilmes) {

        List<Filme> filmes = new ArrayList<>();
        Filme filme = new Filme();

        for(int i = 0; i < quantidadeDeFilmes; i++) {
            filme.setNome(Movie.names().get(i));
            filme.setEstoque(10);
            filme.setPrecoLocacao(100D);
            filmes.add(filme);
        }
        return filmes;
    }

}
