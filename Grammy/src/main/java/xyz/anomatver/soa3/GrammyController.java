//package xyz.anomatver.soa3;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import jakarta.websocket.server.PathParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//
//import java.net.URI;
//import java.util.Collections;
//import java.util.List;
//
//@RestController
//@RequestMapping("grammy/bands")
//public class GrammyController {
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    public String serviceUrl() {
//        List<ServiceInstance> list = discoveryClient.getInstances("musicbands");
//        if (list != null && list.size() > 0 ) {
//            return list.get(0).getUri().toString();
//        }
//        return getRestUrl();
//    }
//
//    public String getRestUrl()  {
//        RestTemplate restTemplate = new RestTemplate();
//        String consulUrl = "localhost:1456";
//
//        try {
//            ResponseEntity<ConsulServiceResponse> responseEntity = restTemplate.getForEntity(
//                    consulUrl + "v1/agent/service/musicbands",
//                    ConsulServiceResponse.class
//            );
//
//            ConsulServiceResponse consulService = responseEntity.getBody();
//
//            if (consulService != null) {
//                return String.format("http://%s:%s/", consulService.getAddress(), consulService.getPort());
//            } else {
//                return "localhost:1454";
//            }
//        } catch (Exception e) {
//            return "localhost:1454";
//        }
//    }
//
//
//
//    @GetMapping("/get-by-genre/{genre}")
//    public ResponseEntity<?> getByGenre(@PathParam("genre") MusicGenre genre) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        String uri = serviceUrl() + "/musicbands?filterBy=genre&filterValue="+genre;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//        ResponseEntity<List<MusicBand>> responseEntity =
//                restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
//
//        List<MusicBand> bandList = responseEntity.getBody();
//        return ResponseEntity.ok(bandList);
//    }
//
//    @PostMapping("/{id}/nominate/{genre}")
//    @ResponseBody
//    public ResponseEntity<String> nominate(@PathVariable Long id, @PathVariable String genre) throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Fetch the band
//        ResponseEntity<MusicBand> responseEntity = restTemplate.exchange(
//                serviceUrl() + "/musicbands/" + id,
//                HttpMethod.GET,
//                null,
//                MusicBand.class
//        );
//
//        if (responseEntity.getStatusCode() != HttpStatus.OK) {
//            throw new HttpClientErrorException(responseEntity.getStatusCode());
//        }
//
//        MusicBand band = responseEntity.getBody();
//
//        // Update the field
//        band.setNominatedToGrammy(true);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<MusicBand> requestEntity = new HttpEntity<>(band, headers);
//
//        // Send a PUT request to update the band
//        ResponseEntity<String> updateResponse = restTemplate.exchange(
//                serviceUrl() + "/musicbands/" + id,
//                HttpMethod.PUT,
//                requestEntity,
//                String.class
//        );
//
//        if (updateResponse.getStatusCode() != HttpStatus.OK) {
//            throw new HttpClientErrorException(updateResponse.getStatusCode());
//        }
//
//        return ResponseEntity.ok("Band has been nominated");
//    }
//
//}
