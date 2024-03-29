package com.ge.exercise1;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    Parser parser;

    @Before
    public void setUp() {
        try {
            Class parserClass = Class.forName("com.ge.exercise1.MyParser");
            parser = (Parser) parserClass.newInstance();
        } catch (Exception e) {
            Assume.assumeNoException(e);
        }
    }

    @Test
    public void parseApplicationDataSimpleTest() {
        String simpleTestData = "Application(id: 0,name: MyApp,users:[User(id: 2,name: Beth Jones)],groups:[Group(id: 1,name: SimpleGroup,users:[User(id: 2,name: Beth Jones)])])";
        Application app = parser.parseApplicationData(simpleTestData);

        assertEquals("SimpleGroup", app.getGroup("1").getName());
        assertEquals(1, app.getGroup("1").size);

        assertEquals("Beth Jones", app.getUser("2").getName());
    }

    @Test
    public void parseApplicationDataComplexTest() {
        String simpleTestData = "Application(id: 0,name: MyApp,users:[User(id: 2,name: Beth Jones),User(id: 3,name: John)],groups:[Group(id: 1,name: SimpleGroup1,users:[User(id: 2,name: Beth Jones)]),Group(id: 2,name: SimpleGroup2,users:[User(id: 3,name: John)])])";
        Application app = parser.parseApplicationData(simpleTestData);

        assertEquals("SimpleGroup1", app.getGroup("1").getName());
        assertEquals(1, app.getGroup("2").size);

        assertEquals("John", app.getUser("3").getName());
    }
}