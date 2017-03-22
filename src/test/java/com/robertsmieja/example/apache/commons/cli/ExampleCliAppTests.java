package com.robertsmieja.example.apache.commons.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ExampleCliAppTests {
    ExampleCliApp objectUnderTest;

    static PrintStream systemOut;
    PrintStream mockSystemOut;

    HelpFormatter helpFormatter;
    Options options;

    @BeforeClass
    public static void backupSystemOut(){
        systemOut = System.out;
    }

    @Before
    public void setup(){
        objectUnderTest = new ExampleCliApp();

        helpFormatter = mock(HelpFormatter.class);
        options = mock(Options.class);

        mockSystemOut = mock(PrintStream.class);
        System.setOut(mockSystemOut);

        objectUnderTest.cliOptions = options;
        objectUnderTest.helpFormatter = helpFormatter;
    }

    @After
    public void restoreSystemOut(){
        System.setOut(systemOut);
    }

    @Test
    public void testHelpOption() throws ParseException {
        objectUnderTest.main(new String[]{});

        verify(helpFormatter).printHelp(ExampleCliApp.APP_NAME, options);
    }
}
