package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.types.files.AttachedFile;
import io.micronaut.views.View;

import java.util.HashMap;
import java.util.Map;

@Controller("/")
public class HomeController {

    protected final BookRepository bookRepository;
    protected final BookExcelService bookExcelService;

    public HomeController(BookRepository bookRepository,
                          BookExcelService bookExcelService) {
        this.bookRepository = bookRepository;
        this.bookExcelService = bookExcelService;
    }

    @View("index")
    @Get
    Map<String, String> index() {
        return new HashMap<>();
    }

    @Get("/excel")
    AttachedFile excel() {
        return bookExcelService.excelFileFromBooks(bookRepository.findAll());
    }
}