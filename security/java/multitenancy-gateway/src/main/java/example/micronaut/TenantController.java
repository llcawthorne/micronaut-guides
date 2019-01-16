package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.multitenancy.tenantresolver.CookieTenantResolverConfiguration;
import io.micronaut.views.View;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller("/tenant")
public class TenantController {
    private final CookieTenantResolverConfiguration cookieTenantResolverConfiguration;

    public TenantController(CookieTenantResolverConfiguration cookieTenantResolverConfiguration) {
        this.cookieTenantResolverConfiguration = cookieTenantResolverConfiguration;
    }

    @View("tenant")
    @Get
    public Map<String, Object> index() {
        Map<String, Object> model = new HashMap<>();
        model.put("pagetitle", "Tenants");
        model.put("tenants", Arrays.asList("sherlock", "watson"));
        return model;
    }

    @Get("/{tenant}")
    public HttpResponse tenant(String tenant) {
        final String path = "/";
        final String cookieName = cookieTenantResolverConfiguration.getCookiename();
        return HttpResponse.status(HttpStatus.FOUND).headers((headers) ->
                headers.location(URI.create(path))
        ).cookie(Cookie.of(cookieName, tenant).path(path));
    }
}