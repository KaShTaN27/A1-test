package by.kashtan.supplies.parser;

import by.kashtan.supplies.exception.InvalidFileException;
import by.kashtan.supplies.rule.ParsingRule;
import by.kashtan.supplies.rule.RulesFactory;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CSVParser implements Parser {

    private final RulesFactory rulesFactory;

    @Override
    public <T> List<T> parse(File file) {
        var separator = determineSeparator(file);
        var ruleName = file.getName().split("\\.")[0];
        try (var reader = configureReader(file, separator)) {
            return parseRecords(reader, ruleName);
        } catch (IOException | CsvException e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    private char determineSeparator(File file) {
        try (var reader = new CSVReader(new FileReader(file))) {
            var header = reader.readNext();
            if (header.length > 1)
                return ',';
            if (header[0].split(";").length > 1)
                return ';';
            else
                return '\t';
        } catch (IOException | CsvValidationException e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    private CSVReader configureReader(File file, char separator) throws FileNotFoundException {
        var parser = new CSVParserBuilder()
                .withSeparator(separator)
                .build();
        return new CSVReaderBuilder(new FileReader(file))
                .withCSVParser(parser)
                .withSkipLines(1)
                .build();
    }

    private <T> List<T> parseRecords(CSVReader reader, String parsingRuleName) throws IOException, CsvException {
        var records = reader.readAll();
        ParsingRule<T> rule = rulesFactory.getRule(parsingRuleName);
        return records.stream()
                .filter(r -> r.length > 1)
                .map(rule::parse)
                .toList();
    }
}
