package xyz.anomatver.soa3;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xyz.anomatver.soa3.generated.GetByGenreRequest;
import xyz.anomatver.soa3.generated.GetByGenreResponse;
import xyz.anomatver.soa3.generated.NominateRequest;
import xyz.anomatver.soa3.generated.NominateResponse;

@Endpoint()
public class SoapEndpoint {
    private static final String NAMESPACE_URI = "http://anomatver.xyz/soa3";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getByGenreRequest")
    @ResponsePayload
    public GetByGenreResponse getMusicBandByGenre(@RequestPayload GetByGenreRequest request) {
        GetByGenreResponse response = new GetByGenreResponse();
        System.out.println("GOVNO");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "nominateRequest")
    @ResponsePayload
    public NominateResponse nominate(@RequestPayload NominateRequest request) {
        NominateResponse response = new NominateResponse();
        return response;
    }

}