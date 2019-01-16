package example.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import java.util.List;

@Client("books")
@Requires(notEnv = Environment.TEST)
public interface BookClient extends BookFetcher {

    @Override
    @Get("/books")
    Single<List<String>> fetchBooks();
}