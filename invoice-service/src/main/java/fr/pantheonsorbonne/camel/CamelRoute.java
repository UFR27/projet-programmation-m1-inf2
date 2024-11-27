package fr.pantheonsorbonne.camel;

import fr.pantheonsorbonne.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("sjms2:M1.InvoiceService").log("Received a new User")
                .unmarshal().json(UserDTO.class)
                .bean("invoice", "createInvoiceForUser")
                .multicast().parallelProcessing()
                .to("direct:sendtToAccountancyService")
                .to("direct:storeLocally");

        from("direct:sendtToAccountancyService")
                .marshal().json().to("sjms2:M1.AccountancyService");

        from("direct:storeLocally")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        UserDTO user = exchange.getMessage().getBody(UserDTO.class);
                        List<List<String>> listOfList = new ArrayList<>();
                        listOfList.add(List.of(user.name(), user.email(), user.invoiceNumber()));
                        exchange.getMessage().setBody(listOfList);


                    }
                })
                .marshal().csv().to("file:data?fileName=invoice_storage.csv&appendChars=\\n&fileExist=Append");
    }
}
