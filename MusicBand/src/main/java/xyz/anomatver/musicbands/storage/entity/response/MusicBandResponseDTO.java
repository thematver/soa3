package xyz.anomatver.musicbands.storage.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicBandResponseDTO {
    private Long id;

    private String name;

    private CoordinatesResponsesDTO coordinates;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    private Long numberOfParticipants;

    private String genre;

    private StudioResponseDTO studio;

    private Boolean isNominatedToGrammy;

    @Data
    public static class CoordinatesResponsesDTO {
        private Float x;
        private Long y;
    }

    @Data
    public static class StudioResponseDTO {
        private String name;
    }
}
