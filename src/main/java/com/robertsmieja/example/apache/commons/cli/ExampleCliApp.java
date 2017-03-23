package com.robertsmieja.example.apache.commons.cli;

import org.apache.commons.cli.*;

/**
 * Example command line application that demonstrates the Apache Commons CLI library
 */
public class ExampleCliApp {
    static final String APP_NAME = ExampleCliApp.class.getSimpleName();

    private static final String HELP_OPTION_ARG = "h";
    private static final String HELP_OPTION_LONG_ARG = "help";
    private static final String ECHO_OPTION_ARG = "e";
    private static final String ECHO_OPTION_LONG_ARG = "echo";
    private static final String ADD_OPTION_ARG_LONG_ARG = "add";

    static HelpFormatter helpFormatter = new HelpFormatter();
    static Options cliOptions = getCommandLineOptions();


    public static void main(String[] args) throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine parsedOptions = commandLineParser.parse(cliOptions, args);

        if (parsedOptions.getOptions().length == 0 || parsedOptions.hasOption(HELP_OPTION_ARG)) {
            helpFormatter.printHelp(APP_NAME, cliOptions);
        }

        if (parsedOptions.hasOption(ECHO_OPTION_LONG_ARG)) {
            String echoArg = parsedOptions.getOptionValue(ECHO_OPTION_LONG_ARG);
            System.out.println(echoArg);
        }

        if (parsedOptions.hasOption(ADD_OPTION_ARG_LONG_ARG)) {
            String[] addArgs = parsedOptions.getOptionValues(ADD_OPTION_ARG_LONG_ARG);
            Integer firstArg = Integer.valueOf(addArgs[0]);
            Integer secondArg = Integer.valueOf(addArgs[1]);
            System.out.println(firstArg + secondArg);
        }
    }

    static Options getCommandLineOptions() {
        Options options = new Options();

        //Example 1 - "Quick add"
        options.addOption(HELP_OPTION_ARG, HELP_OPTION_LONG_ARG, false, "Displays help message");

        //Example 2 - Building out the Option using "new Option()"
        Option echoOption = new Option(ECHO_OPTION_ARG, ECHO_OPTION_LONG_ARG, true, "Echo out the passed argument");
        echoOption.setOptionalArg(false);
        options.addOption(echoOption);

        //Example 3 - Building out the Option using the Builder pattern
        Option additionOption = Option.builder()
                .longOpt(ADD_OPTION_ARG_LONG_ARG)
                .numberOfArgs(2)
                .optionalArg(false)
                .argName("numToAdd otherNumToAdd")
//                .valueSeparator(' ')
                .desc("Add two passed in numbers together")
                .type(Integer.class)
                .build();
        options.addOption(additionOption);
        return options;
    }
}
