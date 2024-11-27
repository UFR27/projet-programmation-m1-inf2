package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.UserDTO;
import fr.pantheonsorbonne.gateway.NotificationGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {

    @Inject
    NotificationGateway gateway;


    @Override
    public void configure() throws Exception {
        from("sjms2:M1.AccountancyService").unmarshal().json(UserDTO.class)
                .choice()
                .when(
                        exchange -> {
                            return exchange.getMessage().getBody(UserDTO.class).email().endsWith("@univ-paris1.fr");
                        }
                )
                .to("direct:processParis1")
                .otherwise()
                .to("direct:deadletter");

        from("direct:processParis1")
                .multicast().parallelProcessing()
                .to("direct:paris1Storage")
                .to("direct:notify")
                .end();

        from("direct:paris1Storage")
                .marshal().jacksonXml().to("file:accountancy");

        from("direct:notify")
                .process(gateway)
                .end();

        from("direct:deadletter")
                .marshal().jacksonXml()
                .log(LoggingLevel.ERROR, "Dead letter encountered: ${body}")
                .to("file:deadletters");


    }
}
