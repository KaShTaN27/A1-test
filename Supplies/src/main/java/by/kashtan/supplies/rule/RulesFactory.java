package by.kashtan.supplies.rule;

import by.kashtan.supplies.exception.UnsupportedParsingRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RulesFactory {

    private final Map<String, ParsingRule> rules;

    public <T> ParsingRule<T> getRule(String ruleName) {
        var rule = rules.get(ruleName);
        if (rule == null)
            throw new UnsupportedParsingRuleException(ruleName);
        return rule;
    }
}
