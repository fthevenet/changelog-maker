package eu.fthevenet.changelog.commands;

import picocli.CommandLine;

import java.text.SimpleDateFormat;

public class DateFormatConverter implements CommandLine.ITypeConverter<SimpleDateFormat> {
    @Override
    public SimpleDateFormat convert(String value) throws Exception {
        return new SimpleDateFormat(value);
    }
}

