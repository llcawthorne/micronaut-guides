package example.micronaut

import grails.gorm.multitenancy.Tenants
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.StartupEvent

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Bootstrap implements ApplicationEventListener<StartupEvent> {

    @Inject
    BookService bookService

    @Override
    void onApplicationEvent(StartupEvent event) {

        Tenants.withId("sherlock") {
            bookService.save('Sherlock diary')
        }
        Tenants.withId("watson") {
            bookService.save('Watson diary')
        }
    }
}