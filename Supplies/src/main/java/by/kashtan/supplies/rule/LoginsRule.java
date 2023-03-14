package by.kashtan.supplies.rule;

import by.kashtan.supplies.model.User;
import org.springframework.stereotype.Component;

@Component("logins")
public class LoginsRule implements ParsingRule<User> {

    @Override
    public User parse(String[] record) {
        return new User(
                record[1].trim(),
                record[3].trim(),
                record[4].trim(),
                Boolean.parseBoolean(record[2].trim()),
                record[0].trim()
        );
    }
}
