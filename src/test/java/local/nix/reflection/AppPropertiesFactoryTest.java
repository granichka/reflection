package local.nix.reflection;

import local.nix.reflection.data.AppProperties;
import local.nix.reflection.factory.impl.AppPropertiesFactory;
import local.nix.reflection.reader.impl.PropertiesFilePropertyReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AppPropertiesFactoryTest {

    @Test
    public void getInstanceMethodTest() {
        AppProperties obj = new AppPropertiesFactory(new PropertiesFilePropertyReader(), "test.properties").getInstance();
        assertEquals(obj.maxConnections, 150);
        assertEquals(obj.language, "English");
        assertEquals(obj.databasePort, "5555");
        assertEquals(obj.nameOfDatabase, "mysql");
        assertFalse(obj.isAdmin);
    }

}
