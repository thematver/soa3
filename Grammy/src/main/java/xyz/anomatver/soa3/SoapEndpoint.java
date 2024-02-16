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

        return "http://localhost:1453/musicbands";

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetByGenreRequest")
    @ResponsePayload
    public GetByGenreResponse GetMusicBandByGenre(@RequestPayload GetByGenreRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        GetByGenreResponse response = new GetByGenreResponse();

        String uri = serviceUrl() + "?filterBy=genre&filterValue="+ request.getGenre();
        System.out.println("GOVNO");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<MusicBand>> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        List<MusicBand> bandList = responseEntity.getBody();

        if (bandList != null) {
            response.getMusicBand().addAll(bandList);
        }

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "NominateRequest")
    @ResponsePayload
    public NominateResponse nominate(@RequestPayload NominateRequest request) {
        NominateResponse response = new NominateResponse();
        RestTemplate restTemplate = new RestTemplate();

        // Fetch the band from the REST service
        String getUri = serviceUrl() + "/musicbands/" + request.getId();
        ResponseEntity<MusicBand> bandResponse = restTemplate.getForEntity(getUri, MusicBand.class);

        if (bandResponse.getStatusCode() == HttpStatus.OK && bandResponse.getBody() != null) {
            MusicBand band = bandResponse.getBody();
            // Update the field for nomination
            band.setNominatedToGrammy(true);

            // Now, send the updated band back to the REST service to update the nomination status
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MusicBand> requestEntity = new HttpEntity<>(band, headers);

            String putUri = serviceUrl() + "/musicbands/" + request.getId();
            ResponseEntity<String> updateResponse = restTemplate.exchange(
                    putUri, HttpMethod.PUT, requestEntity, String.class);


            return response;
        }
        return response;
    }
}