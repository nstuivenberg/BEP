package nl.hu.bep.group4.bifi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import nl.hu.bep.group4.bifi.exceptions.GarbageDataException;
import nl.hu.bep.group4.bifi.lader.factories.FactuurLaderFactory;
import nl.hu.bep.group4.bifi.model.Adres;
import nl.hu.bep.group4.bifi.model.Factuur;
import nl.hu.bep.group4.bifi.model.FactuurRegel;
import nl.hu.bep.group4.bifi.model.Klant;
import nl.hu.bep.group4.bifi.model.Persoon;

public class IntegrationTest {
	@Test
	public void testLader() throws ClassNotFoundException, GarbageDataException, SQLException, IOException {
		var data = FactuurLaderFactory.createFactuurLader().getFacturenVoorMaand(5);

		assertEquals(1, data.size());
		Factuur factuur = data.get(0);
		assertNotNull(factuur);
		
		Klant klant = factuur.getKlant();
		assertNotNull(klant);
		assertEquals(klant.getId(), 3);
		assertEquals(klant.getBedrijfsnaam(), "MarcOtter");
		assertEquals(klant.getRechtsvorm(), "bv");
		assertEquals(klant.getVAT(), "NL001234567B01");
		assertEquals(klant.getBankrekeningNummer(), "NL91ABNA0417164300");
		assertNull(klant.getGiroNummer());
		assertEquals(klant.getBiC(), "DABAIE2D");
		
		assertEquals(klant.getContactPersonen().size(), 1);
		Persoon contactPersoon = klant.getContactPersonen().get(0);
		checkMarineVanHulst(contactPersoon);
		
		assertEquals(klant.getAdres().size(), 0);
		
		Adres factuurAdres = klant.getFactuurAdres();
		assertEquals(factuurAdres.getStraat(), "Wassenlaan");
		assertEquals(factuurAdres.getHuisnummer(), "358");
		assertEquals(factuurAdres.getPostcode(), "4302CD");
		assertEquals(factuurAdres.getPlaats(), "Zevenhuizen");
		assertNull(factuurAdres.getBiC());
		
		assertEquals(factuur.getDatumtijd(), "Sat Jun 16 12:23:40 CEST 2018");
		assertEquals(factuur.getFactuurNummer(), 1);
		
		assertEquals(factuur.getFactuurregels().size(), 1);
		FactuurRegel factuurRegel = factuur.getFactuurregels().get(0);
		assertNotNull(factuurRegel);
		assertEquals(factuurRegel.getProductID(), 1);
		assertEquals(factuurRegel.getProductNaam(), "BiFi worstjes voordeelstrip");
		assertEquals(factuurRegel.getAantal(), 20);
		assertEquals(factuurRegel.getTotaalprijsExBTW(), 30);
		assertEquals(factuurRegel.getBtwCode(), FactuurRegel.BTWcode.HOOG);
		assertEquals(factuurRegel.getUnit(), FactuurRegel.Unit.KILOGRAM);
		
		assertNull(factuur.getOpmerking());
		
		checkMarineVanHulst(factuur.getContactPersoon());		
	}

	private void checkMarineVanHulst(Persoon persoon) {
		assertNotNull(persoon);
		assertEquals(persoon.getId(), 3);
		assertEquals(persoon.getVoornaam(), "Marine");
		assertEquals(persoon.getAchternaam(), "Hulst");
		assertEquals(persoon.getTussenvoegsel(), "van");
		assertEquals(persoon.getTelefoon(), "2147483647");
		assertEquals(persoon.getFax(), "test@test.com");
		assertEquals(persoon.getGeslacht(), Persoon.Geslacht.VROUW);
	}
	
}
