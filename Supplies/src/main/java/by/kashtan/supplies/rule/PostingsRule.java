package by.kashtan.supplies.rule;

import by.kashtan.supplies.model.PostedProduct;
import by.kashtan.supplies.model.Posting;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component("postings")
public class PostingsRule implements ParsingRule<Posting> {

    @Override
    public Posting parse(String[] record) {
        var dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            var postingNumber = Long.parseLong(record[0]);
            var docDate = dateFormat.parse(record[2].trim());
            var postingDate = dateFormat.parse(record[3].trim());
            var postedProducts = parsePostedProduct(record);
            var username = record[9].trim();
            return new Posting(postingNumber,docDate,postingDate, postedProducts, username);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PostedProduct> parsePostedProduct(String[] record) throws ParseException {
        var products = new ArrayList<PostedProduct>();
        var desc = record[4].trim();
        var quantity = Integer.parseInt(record[5].trim());
        var bUn = record[6].trim();
        var format = NumberFormat.getInstance(Locale.FRANCE);
        var amount = format.parse(record[7].trim());
        var currency = record[8].trim();
        var product = new PostedProduct(desc, quantity, bUn, amount.doubleValue(), currency);
        products.add(product);
        return products;
    }
}
