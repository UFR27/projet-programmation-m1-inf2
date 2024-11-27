package fr.pantheonsorbonne.gateway;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(baseUri = "https://sanspapier.nextnet.top/")
public interface NotificationResource {

    @POST
    @Path("notif_m1")
    public Response pushNotif(String str);
}
