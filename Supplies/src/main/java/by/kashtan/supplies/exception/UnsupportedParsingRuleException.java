package by.kashtan.supplies.exception;

public class UnsupportedParsingRuleException extends RuntimeException{
    public UnsupportedParsingRuleException(String ruleName) {
        super(String.format("Parsing rule %s does not supported", ruleName));
    }
}
