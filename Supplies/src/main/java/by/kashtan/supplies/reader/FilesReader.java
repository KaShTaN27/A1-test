package by.kashtan.supplies.reader;

import by.kashtan.supplies.model.Posting;
import by.kashtan.supplies.model.User;
import by.kashtan.supplies.parser.CSVParser;
import by.kashtan.supplies.service.PostingService;
import by.kashtan.supplies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FilesReader {

    private final PostingService postingService;
    private final UserService userService;
    private final CSVParser parser;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartUp() {
        List<User> users = readLoginsCsv();
        List<Posting> postings = readPostingsCsv();
        checkPostingAuthentication(postings, users);
        userService.saveAll(users);
        postingService.saveAll(postings);
    }

    private List<User> readLoginsCsv() {
        var loginsCsv = new File("src/main/resources/files/logins.csv");
        return parser.parse(loginsCsv);
    }

    private List<Posting> readPostingsCsv() {
        var postingsCsv = new File("src/main/resources/files/postings.csv");
        List<Posting> parsed = parser.parse(postingsCsv);
        parsed = parsed.stream()
                .sorted(Comparator.comparing(Posting::getPostingNumber))
                .toList();
        return getUniquePostings(parsed);
    }

    private List<Posting> getUniquePostings(List<Posting> postings) {
        var currPostingNumber = -1L;
        var unique = new ArrayList<Posting>();
        for (Posting posting : postings) {
            if (currPostingNumber == posting.getPostingNumber()) {
                var index = unique.size() - 1;
                unique.get(index).getProducts().addAll(posting.getProducts());
            } else {
                unique.add(posting);
                currPostingNumber = posting.getPostingNumber();
            }
        }
        return unique;
    }

    private void checkPostingAuthentication(List<Posting> postings, List<User> users) {
        postings.forEach(p -> {
            var isAuthenticated = users.stream()
                    .anyMatch(u -> u.getAccountName().equals(p.getUsername()) && u.getIsActive());
            p.setIsAuthenticated(isAuthenticated);
        });
    }
}
