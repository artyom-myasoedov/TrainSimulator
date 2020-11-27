package myasoedov.cs;

import junit.framework.TestCase;

public class ConfigsTest extends TestCase {

    public void testLoad() {
        assertEquals("jdbc:h2:D:\\Projects\\Idea projects\\TrainSimulator\\src\\db\\TrainSimulator",
                Configs.DBProperties.getProperty("db.url"));
    }
}
