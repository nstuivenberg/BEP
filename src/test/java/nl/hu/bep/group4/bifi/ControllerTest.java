package nl.hu.bep.group4.bifi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.hu.bep.group4.bifi.interfaces.FactuurLader;
import nl.hu.bep.group4.bifi.interfaces.IEFExporter;
import nl.hu.bep.group4.bifi.model.Factuur;

public class ControllerTest {
	@Test
	public void test1() {
		testMaandNummer(1);
	}
	
	@Test
	public void test2() {
		testMaandNummer(2);
	}
	
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
	
	private void testMaandNummer(int testMaandNummer) {
		testMaandNummerStrings(testMaandNummer, new String[] {""+testMaandNummer});
	}
	
	private void testMaandNummerStrings(int testMaandNummer, String[] arguments) {
		final ArrayList<Factuur> testData = new ArrayList<Factuur>();
		testData.add(new Factuur(null, null, 0, null, null, null));
		var exporter = new IEFExporter() {
			public boolean exported = false;
			public void exportFacturen(List<Factuur> facturen) {
				assertNotNull(facturen);
				assertEquals(facturen, testData);
				exported = true;
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
