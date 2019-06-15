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
		assertEquals(3, klant.getId());
		assertEquals("MarcOtter", klant.getBedrijfsnaam());
		assertEquals("bv", klant.getRechtsvorm());
		assertEquals("NL001234567B01", klant.getVAT());
		assertEquals("NL91ABNA0417164300", klant.getBankrekeningNummer());
		assertNull(klant.getGiroNummer());
		assertEquals("DABAIE2D", klant.getBiC());
		
		assertEquals(1, klant.getContactPersonen().size());
		Persoon contactPersoon = klant.getContactPersonen().get(0);
		checkMarineVanHulst(contactPersoon);
		
		assertEquals(0, klant.getAdres().size());
		
		Adres factuurAdres = klant.getFactuurAdres();
		assertEquals("Wassenlaan", factuurAdres.getStraat());
		assertEquals("358", factuurAdres.getHuisnummer());
		assertEquals("4302CD", factuurAdres.getPostcode());
		assertEquals("Zevenhuizen", factuurAdres.getPlaats());
		assertNull(factuurAdres.getBic());
		
		assertEquals("2018-06-16T10:23:40.049Z", factuur.getDatumtijd());
		assertEquals(1, factuur.getFactuurNummer());
		
		assertEquals(1, factuur.getFactuurregels().size());
		FactuurRegel factuurRegel = factuur.getFactuurregels().get(0);
		assertNotNull(factuurRegel);
		assertEquals(1, factuurRegel.getProductID());
		assertEquals("BiFi worstjes voordeelstrip", factuurRegel.getProductNaam());
		assertEquals(20, factuurRegel.getAantal());
		assertEquals(30, factuurRegel.getTotaalprijsExBTW());
		assertEquals(FactuurRegel.BTWcode.HOOG, factuurRegel.getBtwCode());
		assertEquals(FactuurRegel.Unit.KILOGRAM, factuurRegel.getUnit());
		
		assertNull(factuur.getOpmerking());
		
		checkMarineVanHulst(factuur.getContactPersoon());		
	}

	private void checkMarineVanHulst(Persoon persoon) {
		assertNotNull(persoon);
		assertEquals(3, persoon.getId());
		assertEquals("Marine", persoon.getVoornaam());
		assertEquals("Hulst", persoon.getAchternaam());
		assertEquals("van", persoon.getTussenvoegsel());
		assertEquals("2147483647", persoon.getTelefoon());
		assertEquals("test@test.com", persoon.getFax());
		assertEquals(Persoon.Geslacht.VROUW, persoon.getGeslacht());
	}
}
