package matchers;

import br.com.elthonfa.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana) {
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda() {
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiasMatcher ehHojeComDiferencaDias(int dias) {
        return new DataDiferencaDiasMatcher(dias);
    }

    public static Date ehHoje() {
        return new Date();
    }
}
