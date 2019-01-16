package example.micronaut

import grails.gorm.MultiTenant
import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
class Book implements GormEntity<Book>, MultiTenant {
    String title

    static constraints = {
        title nullable: false, blank: false
    }
}