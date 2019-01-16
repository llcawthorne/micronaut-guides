package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class ClientUsingSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer)

    def "Verify HTTP Basic Auth works"() {
        when:
        AppClient appClient = embeddedServer.applicationContext.getBean(AppClient)

        then:
        noExceptionThrown()

        when:
        String credsEncoded = "sherlock:password".bytes.encodeBase64().toString()
        String rsp = appClient.home("Basic ${credsEncoded}")

        then:
        rsp == 'sherlock'
    }
}