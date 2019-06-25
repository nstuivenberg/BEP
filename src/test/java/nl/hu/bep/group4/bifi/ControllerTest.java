package nl.hu.bep.group4.bifi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.hu.bep.group4.bifi.exceptions.GarbageDataException;
import nl.hu.bep.group4.bifi.interfaces.FactuurLader;
import nl.hu.bep.group4.bifi.interfaces.IEFExporter;
import nl.hu.bep.group4.bifi.model.Factuur;

public class ControllerTest {
	@Test
	public void testInvalidNumber() {
		Assertions.assertThrows(NumberFormatException.class, () -> {
			testMaandNummerStrings(1, new String[] {"onzin"});
		});
	}
	
	@Test
	public void testNoArgument() {
		Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			testMaandNummerStrings(1, new String[] {});
		});
	}
	
	private void testMaandNummerStrings(int testMaandNummer, String[] arguments) throws ClassNotFoundException, GarbageDataException, SQLException, IOException {
		final ArrayList<Factuur> testData = new ArrayList<>();
		testData.add(new Factuur(null, null, 0, null, null, null));
		var exporter = new IEFExporter() {
			public boolean exported = false;
			public String exportFacturen(List<Factuur> facturen) {
				assertNotNull(facturen);
				assertEquals(facturen, testData);
				exported = true;
				return null;
			}
		};
		Controller c = new Controller(new FactuurLader() {
			public List<Factuur> getFacturenVoorMaand(int maandNummer) {
				assertEquals(maandNummer, testMaandNummer);
				return testData;
			}
		}, exporter);
		c.main(arguments);
		assertTrue(exporter.exported);
	}
}
