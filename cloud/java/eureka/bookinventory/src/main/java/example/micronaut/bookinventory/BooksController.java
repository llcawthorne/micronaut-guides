package example.micronaut.bookinventory;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Validated
@Controller("/books")
public class BooksController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/stock/{isbn}")
    public Boolean stock(@NotBlank String isbn) {
        return bookInventoryByIsbn(isbn).map(bi -> bi.getStock() > 0).orElse(null);
    }

    private Optional<BookInventory> bookInventoryByIsbn(String isbn) {
        if(isbn.equals("1491950358")) {
            return Optional.of(new BookInventory(isbn, 4));

        } else if(isbn.equals("1680502395")) {
            return Optional.of(new BookInventory(isbn, 0));
        }
        return Optional.empty();
    }
}