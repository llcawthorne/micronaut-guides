package example.micronaut;

import java.util.List;
import io.micronaut.http.server.types.files.AttachedFile;

public interface BookExcelService {
    static final String SHEET_NAME = "Books";
    static final String HEADER_ISBN = "Isbn";
    static final String HEADER_NAME = "Name";
    static final String HEADER_EXCEL_FILE_SUFFIX = ".xlsx";
    static final String HEADER_EXCEL_FILE_PREFIX = "books";
    static final String HEADER_EXCEL_FILENAME = HEADER_EXCEL_FILE_PREFIX + HEADER_EXCEL_FILE_SUFFIX;

    AttachedFile excelFileFromBooks(List<Book> bookList);
}