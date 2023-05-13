package suites;

import br.com.elthonfa.services.CalculadoraTest;
import br.com.elthonfa.services.CalculoValorLocacaoTest;
import br.com.elthonfa.services.LocalServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculadoraTest.class,
        CalculoValorLocacaoTest.class,
        LocalServiceTest.class
})
public class SuiteExecucao {
}
