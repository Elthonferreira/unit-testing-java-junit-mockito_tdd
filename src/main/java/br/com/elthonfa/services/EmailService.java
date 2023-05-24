package br.com.elthonfa.services;

import br.com.elthonfa.entities.Usuario;

public interface EmailService {

    public void notificarAtraso(Usuario usuario);
}
