package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;
import io.reactivex.Single;

import java.util.HashMap;
import java.util.Map;

@Controller("/")
public class HomeController {

    private final BookFetcher bookFetcher;

    public HomeController(BookFetcher bookFetcher) {
        this.bookFetcher = bookFetcher;
    }

    @View("home")
    @Get
    Single<HttpResponse<Map<String, Object>>> index() {
        return bookFetcher.fetchBooks().map(books -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pagetitle", "Home");
            model.put("books", books);
            return HttpResponse.ok(model);
        });
    }

}