package xyz.anomatver.soa3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xyz.anomatver.soa3.generated.*;

import java.util.Collections;
import java.util.List;

@Endpoint()
public class SoapEndpoint {
    private static final String NAMESPACE_URI = "http://anomatver.xyz/soa3";


    private String serviceUrl() {

        return "http://localhost:1454/musicbands";

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getByGenreRequest")
    @ResponsePayload
    public GetByGenreResponse getMusicBandByGenre(@RequestPayload GetByGenreRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        GetByGenreResponse response = new GetByGenreResponse();

        String uri = serviceUrl() + "/musicbands?filterBy=genre&filterValue="+ request.getGenre();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<xyz.anomatver.soa3.generated.MusicBand> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        List<MusicBand> bandList = responseEntity.getBody();
        if (bandList != null) {
            response.getMusicBand().addAll(bandList);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "nominateRequest")
    @ResponsePayload
    public NominateResponse nominate(@RequestPayload NominateRequest request) {
        NominateResponse response = new NominateResponse();
        return response;
    }

}