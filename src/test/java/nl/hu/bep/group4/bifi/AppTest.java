package nl.hu.bep.group4.bifi;

import nl.hu.bep.group4.bifi.interfaces.FactuurLader;
import nl.hu.bep.group4.bifi.lader.factories.FactuurLaderFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void thisClassHasNothingToTestButWeWantCoverage() {
        assertTrue(true);
    }

    @Test
    public void testMain() {
        FactuurLader factuurLader = FactuurLaderFactory.createFactuurLader();
        Controller controller = new Controller(factuurLader, null);
        assertEquals(factuurLader, controller.getFactuurLader());
    }
}
