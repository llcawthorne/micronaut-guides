package example.micronaut

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.services.Service

@CurrentTenant
@Service(Book)
interface BookService {
    Book save(String title)
    List<Book> findAll()
}