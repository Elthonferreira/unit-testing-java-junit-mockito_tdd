package builders;

import br.com.elthonfa.entities.Usuario;

public class UsuarioBuilder {
    private Usuario usuario;

    private UsuarioBuilder () {}

    public static Usuario umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("Elthon Ferreira");

        return builder.usuario;
    }

}
