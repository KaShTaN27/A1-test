package by.kashtan.supplies.controller;

import by.kashtan.supplies.model.Posting;
import by.kashtan.supplies.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/postings")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping
    ResponseEntity<List<Posting>> getAllPostings(
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date to,
            @RequestParam(name = "isAuth", required = false) Boolean isAuth) {
        return ResponseEntity.ok(postingService.getAll(from, to, isAuth));
    }
}
