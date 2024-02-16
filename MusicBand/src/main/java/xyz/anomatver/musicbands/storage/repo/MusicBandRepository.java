package xyz.anomatver.musicbands.storage.repo;

import xyz.anomatver.musicbands.storage.entity.MusicBand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicBandRepository extends JpaRepository<MusicBand, Long> {
    List<MusicBand> findAllById(Long id);

    Boolean existsByNumberOfParticipants(Long numberOfParticipants);

    void deleteAllByNumberOfParticipants(Long numberOfParticipants);

    List<MusicBand> findAllByNumberOfParticipants(Long numberOfParticipants);

    Long countMusicBandByNumberOfParticipants(Long numberOfParticipants);
}
