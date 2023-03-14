package by.kashtan.supplies.rule;

import by.kashtan.supplies.model.PostedProduct;
import by.kashtan.supplies.model.Posting;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component("postings")
public class PostingsRule implements ParsingRule<Posting> {

    @Override
    public Posting parse(String[] record) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Long postingNumber = Long.parseLong(record[0]);
            Date docDate = dateFormat.parse(record[2].trim());
            Date postingDate = dateFormat.parse(record[3].trim());
            List<PostedProduct> postedProducts = parsePostedProduct(record);
            String username = record[9].trim();
            return new Posting(postingNumber,docDate,postingDate, postedProducts, username);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<PostedProduct> parsePostedProduct(String[] record) throws ParseException {
        List<PostedProduct> products = new ArrayList<>();
        String desc = record[4].trim();
        Integer quantity = Integer.parseInt(record[5].trim());
        String bUn = record[6].trim();
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number amount = format.parse(record[7].trim());
        String currency = record[8].trim();
        PostedProduct product = new PostedProduct(desc, quantity, bUn, amount.doubleValue(), currency);
        products.add(product);
        return products;
    }
}
