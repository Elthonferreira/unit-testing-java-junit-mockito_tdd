package br.com.elthonfa.services;

import br.com.elthonfa.entities.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test() {
        Assert.assertTrue(true);
        Assert.assertFalse(false);
        Assert.assertEquals(1, 1);
        Assert.assertEquals("Erro de comparaçao", 1, 1); // No caso de erro, a mensagem "Erro de comparaçao" e exibida.
        Assert.assertEquals(0.25, 0.25, 0.01); // Para comparaçao de numeros double, e necessario um delta que sera a margem de erro de comparaçao.
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        // Para comparar objetos com tipos primitivos, e necessario fazer uma conversao
        int valorInteiroTipoPrimitivo = 10;
        Integer valorInteiroObjeto = 10;

        Assert.assertEquals(Integer.valueOf(valorInteiroTipoPrimitivo), valorInteiroObjeto);
        Assert.assertEquals(valorInteiroTipoPrimitivo, valorInteiroObjeto.intValue());

        // Comparaçao de Strings
        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "Bola");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        // Compraçao de objetos
        Usuario usuario1 = new Usuario("Elthon");
        Usuario usuario2 = new Usuario("Elthon");
        Usuario usuario3 = null;

        Assert.assertEquals(usuario1, usuario2); // So funciona porque tem um metodo equals em Usuario que faz a comparaçao de nomes, se nao fosse isso, seria comparado as instancias que seriam diferentes
        Assert.assertSame(usuario1, usuario1); // Compara instancias de objetos
        Assert.assertNotSame(usuario1, usuario2);
        Assert.assertNull(usuario3);
        Assert.assertNotNull(usuario2);
    }
}
