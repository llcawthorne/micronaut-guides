package example.micronaut

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/books")
class BookController {

    private final BookService bookService

    BookController(BookService bookService) {
        this.bookService = bookService
    }

    @Get
    List<String> index() {
        bookService.findAll()*.title
    }
}