package nl.hu.bep.group4.bifi;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.hu.bep.group4.bifi.interfaces.FactuurLader;
import nl.hu.bep.group4.bifi.interfaces.IEFExporter;
import nl.hu.bep.group4.bifi.model.Factuur;

public class ControllerTest {
	@Test
	public void test() {
		var exporter = new IEFExporter() {
			public boolean exported = false;
			public void exportFacturen(List<Factuur> facturen) {
				exported = true;
			}
		};
		Controller c = new Controller(new FactuurLader() {
			public List<Factuur> getFacturenVoorMaand(int maandNummer) {
				return new ArrayList<Factuur>();
			}
		}, exporter);
		c.main(new String[] {"", "1"});
		assertTrue(exporter.exported);
	}
}
