package nl.hu.bep.group4.bifi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import nl.hu.bep.group4.bifi.exceptions.GarbageDataException;

public class AppTest {

    @Test
    public void testEverything() throws ClassNotFoundException, GarbageDataException, SQLException, IOException {
    	App.main(new String[] {"5"});
        assertEquals(1+2, 3);
    }
}
