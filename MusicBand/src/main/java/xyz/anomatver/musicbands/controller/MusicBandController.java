package xyz.anomatver.musicbands.controller;

import xyz.anomatver.musicbands.service.MusicBandService;
import xyz.anomatver.musicbands.storage.entity.Coordinates;
import xyz.anomatver.musicbands.storage.entity.MusicBand;
import xyz.anomatver.musicbands.storage.entity.Studio;
import xyz.anomatver.musicbands.storage.entity.request.MusicBandRequestDTO;
import xyz.anomatver.musicbands.storage.entity.response.CountResponseDTO;
import xyz.anomatver.musicbands.storage.entity.response.Error;
import xyz.anomatver.musicbands.storage.entity.response.MusicBandPageableResponseDTO;
import xyz.anomatver.musicbands.storage.entity.response.MusicBandResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musicbands")
public class MusicBandController {
    @Autowired
    MusicBandService musicBandService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMusicBandById(@PathVariable Long id) {
        List<MusicBand> musicBandList = musicBandService.getMusicBandById(id);
        if (musicBandList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Error.builder()
                            .message("MusicBand with id = " + id + " does not exist.")
                            .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                            .build());
        }

        MusicBand musicBand = musicBandList.get(0);
        MusicBandResponseDTO musicBandResponseDTO = new MusicBandResponseDTO();

        toDTO(musicBand, musicBandResponseDTO);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(musicBandResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMusicBandById(@PathVariable Long id) {
        Boolean result = musicBandService.deleteMusicBandById(id);
        if (!result) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Error.builder()
                            .message("MusicBand with id = " + id + " does not exist.")
                            .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                            .build());
        }

        return ResponseEntity.ok("MusicBand with id = " + id + " was successfully deleted.");
    }

    @PostMapping
    public ResponseEntity<?> addMusicBand(@Valid @RequestBody MusicBandRequestDTO musicBandRequestDTO) {
        MusicBand musicBand = new MusicBand();

        musicBand.setCreationDate(LocalDateTime.now());

        musicBandService.addMusicBand(fromDTO(musicBand, musicBandRequestDTO));

        MusicBandResponseDTO musicBandResponseDTO = new MusicBandResponseDTO();

        toDTO(musicBand, musicBandResponseDTO);

        return ResponseEntity.ok(musicBandResponseDTO);
    }

    @DeleteMapping("/filter")
    public ResponseEntity<?> deleteAllMusicBandsByNumberOfParticipants(@RequestParam Long numberOfParticipants) {
        Boolean result = musicBandService.deleteAllByParticipants(numberOfParticipants);
        if (!result) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Error.builder()
                            .message("MusicBand with numberOfParticipants = " + numberOfParticipants + " does not exist.")
                            .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                            .build());
        }

        return ResponseEntity.ok("All musicBands were successfully deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMusicBandById(@PathVariable Long id, @RequestBody MusicBandRequestDTO musicBandRequestDTO) {
        List<MusicBand> musicBandList = musicBandService.getMusicBandById(id);
        if (musicBandList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Error.builder()
                            .message("MusicBand with id = " + id + " does not exist.")
                            .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                            .build());
        }

        MusicBand musicBand = musicBandList.get(0);

        musicBandService.addMusicBand(fromDTO(musicBand, musicBandRequestDTO));

        MusicBandResponseDTO musicBandResponseDTO = new MusicBandResponseDTO();
        toDTO(musicBand, musicBandResponseDTO);

        return ResponseEntity.ok(musicBandResponseDTO);
    }

    @DeleteMapping("/filter/first")
    public ResponseEntity<?> deleteFirstByParticipants(@RequestParam Long numberOfParticipants) {
        List<MusicBand> musicBandList = musicBandService.getAllMusicBandByParticipants(numberOfParticipants);
        if (musicBandList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Error.builder()
                            .message("MusicBand with numberOfParticipants = " + numberOfParticipants + " does not exist.")
                            .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                            .build());
        }

        musicBandService.deleteMusicBandById(musicBandList.get(0).getId());
        return ResponseEntity.ok("MusicBand was deleted successfully");
    }

    @GetMapping("/count")
    public ResponseEntity<?> countMusicBand(@RequestParam Long numberOfParticipants) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new CountResponseDTO(musicBandService.getCountOfMusicBands(numberOfParticipants)));
    }

    @GetMapping
    public ResponseEntity<?> findAllMusicBandByPage(HttpServletRequest httpServletRequest) {
        Integer page;
        Integer limit;
        if (httpServletRequest.getParameter("page") == null) {
            page = 1;
        }
        else {
            page = Integer.parseInt(httpServletRequest.getParameter("page"));
        }
        if (httpServletRequest.getParameter("limit") == null) {
            limit = 2;
        }
        else {
            limit = Integer.parseInt(httpServletRequest.getParameter("limit"));
        }
        List<MusicBand> musicBandList = musicBandService.findAll(httpServletRequest, page, limit);
        List<MusicBandResponseDTO> musicBandResponseDTOList = new ArrayList<>();
        //MusicBandPageableResponseDTO musicBandPageableResponseDTO = new MusicBandPageableResponseDTO();
        toDTOList(musicBandList, musicBandResponseDTOList);
        //toPageableDTO(musicBandList, musicBandPageableResponseDTO, page);
        return ResponseEntity.ok(musicBandResponseDTOList);
    }

    private void toPageableDTO(List<MusicBand> musicBandList, MusicBandPageableResponseDTO musicBandPageableResponseDTO, Integer page) {
        List<MusicBandResponseDTO> musicBandResponseDTOList = new ArrayList<>();

        for (MusicBand mb: musicBandList) {
            MusicBandResponseDTO musicBandResponseDTO = new MusicBandResponseDTO();
            toDTO(mb, musicBandResponseDTO);
            musicBandResponseDTOList.add(musicBandResponseDTO);
        }

        musicBandPageableResponseDTO.setMusicBandResponseDTOList(musicBandResponseDTOList);
        musicBandPageableResponseDTO.setPageNumber(page);
    }

    private MusicBand fromDTO(MusicBand musicBand, MusicBandRequestDTO musicBandRequestDTO) {
        musicBand.setName(musicBandRequestDTO.getName());

        Coordinates coordinates = new Coordinates();
        coordinates.setX(musicBandRequestDTO.getCoordinates().getX());
        coordinates.setY(musicBandRequestDTO.getCoordinates().getY());
        musicBand.setCoordinates(coordinates);

        musicBand.setNumberOfParticipants(musicBandRequestDTO.getNumberOfParticipants());
        musicBand.setGenre(musicBandRequestDTO.getGenre());

        Studio studio = new Studio();
        if (musicBandRequestDTO.getStudio().getName() != null) {
            studio.setName(musicBandRequestDTO.getStudio().getName());
        }
        musicBand.setStudio(studio);

        if(musicBandRequestDTO.getIsNominatedToGrammy() != null) {
            musicBand.setIsNominatedToGrammy(musicBandRequestDTO.getIsNominatedToGrammy());
        }

        return musicBand;
    }

    private void toDTO(MusicBand musicBand, MusicBandResponseDTO musicBandResponseDTO) {
        musicBandResponseDTO.setId(musicBand.getId());
        musicBandResponseDTO.setName(musicBand.getName());

        MusicBandResponseDTO.CoordinatesResponsesDTO coordinatesResponsesDTO = new MusicBandResponseDTO.CoordinatesResponsesDTO();
        coordinatesResponsesDTO.setX(musicBand.getCoordinates().getX());
        coordinatesResponsesDTO.setY(musicBand.getCoordinates().getY());
        musicBandResponseDTO.setCoordinates(coordinatesResponsesDTO);

        musicBandResponseDTO.setCreationDate(musicBand.getCreationDate());
        musicBandResponseDTO.setNumberOfParticipants(musicBand.getNumberOfParticipants());
        musicBandResponseDTO.setGenre(musicBand.getGenre());

        MusicBandResponseDTO.StudioResponseDTO studioResponseDTO = new MusicBandResponseDTO.StudioResponseDTO();
        studioResponseDTO.setName(musicBand.getStudio().getName());
        musicBandResponseDTO.setStudio(studioResponseDTO);

        musicBandResponseDTO.setIsNominatedToGrammy(musicBand.getIsNominatedToGrammy());
    }

    private void toDTOList(List<MusicBand> musicBandList, List<MusicBandResponseDTO> musicBandResponseDTOList) {
        MusicBandResponseDTO musicBandResponseDTO;
        for (MusicBand musicBand: musicBandList) {
            musicBandResponseDTO = new MusicBandResponseDTO();
            musicBandResponseDTO.setId(musicBand.getId());
            musicBandResponseDTO.setName(musicBand.getName());

            MusicBandResponseDTO.CoordinatesResponsesDTO coordinatesResponsesDTO = new MusicBandResponseDTO.CoordinatesResponsesDTO();
            coordinatesResponsesDTO.setX(musicBand.getCoordinates().getX());
            coordinatesResponsesDTO.setY(musicBand.getCoordinates().getY());
            musicBandResponseDTO.setCoordinates(coordinatesResponsesDTO);

            musicBandResponseDTO.setCreationDate(musicBand.getCreationDate());
            musicBandResponseDTO.setNumberOfParticipants(musicBand.getNumberOfParticipants());
            musicBandResponseDTO.setGenre(musicBand.getGenre());
            musicBandResponseDTO.setIsNominatedToGrammy(musicBand.getIsNominatedToGrammy());

            MusicBandResponseDTO.StudioResponseDTO studioResponseDTO = new MusicBandResponseDTO.StudioResponseDTO();
            studioResponseDTO.setName(musicBand.getStudio().getName());
            musicBandResponseDTO.setStudio(studioResponseDTO);
            musicBandResponseDTOList.add(musicBandResponseDTO);
        }
    }
}
