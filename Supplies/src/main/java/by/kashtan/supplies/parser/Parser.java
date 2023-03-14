package by.kashtan.supplies.parser;

import java.io.File;
import java.util.List;

public interface Parser {
    <T> List<T> parse(File file);
}
