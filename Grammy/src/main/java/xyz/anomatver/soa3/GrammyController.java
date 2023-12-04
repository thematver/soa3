package xyz.anomatver.soa3;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.websocket.server.PathParam;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import xyz.anomatver.soa3.model.MusicBand;
import xyz.anomatver.soa3.model.MusicGenre;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("grammy/bands")
public class GrammyController {

    @GetMapping("/get-by-genre/{genre}")
    public ResponseEntity<?> getByGenre(@PathParam("genre") MusicGenre genre) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:8444/soa-first-0.0.1-SNAPSHOT/musicbands?filterBy=genre&filterValue="+genre;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<MusicBand>> responseEntity =
                restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        List<MusicBand> bandList = responseEntity.getBody();
        return ResponseEntity.ok(bandList);
    }

    @PostMapping("/{id}/nominate/{genre}")
    @ResponseBody
    public ResponseEntity<String> nominate(@PathVariable Long id, @PathVariable String genre) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        // Fetch the band
        ResponseEntity<MusicBand> responseEntity = restTemplate.exchange(
                "http://localhost:8444/soa-first-0.0.1-SNAPSHOT/musicbands/" + id,
                HttpMethod.GET,
                null,
                MusicBand.class
        );

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new HttpClientErrorException(responseEntity.getStatusCode());
        }

        MusicBand band = responseEntity.getBody();

        // Update the field
        band.setNominatedToGrammy(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MusicBand> requestEntity = new HttpEntity<>(band, headers);

        // Send a PUT request to update the band
        ResponseEntity<String> updateResponse = restTemplate.exchange(
                "https://localhost:8444/soa-first-0.0.1-SNAPSHOT/musicbands/" + id,
                HttpMethod.PUT,
                requestEntity,
                String.class
        );

        if (updateResponse.getStatusCode() != HttpStatus.OK) {
            throw new HttpClientErrorException(updateResponse.getStatusCode());
        }

        return ResponseEntity.ok("Band has been nominated");
    }

}
