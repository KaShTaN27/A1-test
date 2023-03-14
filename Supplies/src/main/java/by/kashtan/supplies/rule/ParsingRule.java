package by.kashtan.supplies.rule;

public interface ParsingRule<T> {
    T parse(String[] record);
}
