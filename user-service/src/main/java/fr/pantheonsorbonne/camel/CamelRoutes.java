package fr.pantheonsorbonne.camel;

import org.apache.camel.builder.RouteBuilder;

public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:newUser")
                .log("new user sent to Invoice MS")
                .marshal().json()
                .to("sjms2:M1.InvoiceService");
    }
}
