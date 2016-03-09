package com.reagroup.utils;

import com.reagroup.commands.*;
import junit.framework.TestCase;

/**
 * @author <a href="mailto:guido.barbaglia@gmail.com">Guido Barbaglia</a>
 */
public class TestStringToCommandConverter extends TestCase {

    Command c;

    public void testConvert() {
        try {
            c = StringToCommandConverter.convert("place 0,0,north");
            assertTrue(c instanceof Place);
        } catch (Exception e) {
            fail();
        }
        try {
            c = StringToCommandConverter.convert("move");
            assertTrue(c instanceof Move);
        } catch (Exception e) {
            fail();
        }
        try {
            c = StringToCommandConverter.convert("left");
            assertTrue(c instanceof Left);
        } catch (Exception e) {
            fail();
        }
        try {
            c = StringToCommandConverter.convert("right");
            assertTrue(c instanceof Right);
        } catch (Exception e) {
            fail();
        }
        try {
            c = StringToCommandConverter.convert("jump");
        } catch (Exception e) {
            assertEquals("Please provide a valid command", e.getMessage());
        }
    }

}