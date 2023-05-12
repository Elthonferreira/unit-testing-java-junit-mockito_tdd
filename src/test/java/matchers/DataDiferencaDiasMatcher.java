package matchers;

import br.com.elthonfa.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer quantidadeDeDias;

    public DataDiferencaDiasMatcher(Integer quantidadeDeDias) {
        this.quantidadeDeDias = quantidadeDeDias;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantidadeDeDias));
    }

    public void describeTo(Description description) {

    }

}
