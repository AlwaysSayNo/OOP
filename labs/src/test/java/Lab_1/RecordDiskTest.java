package test.java.Lab_1;


import Lab_1.disk.RecordDisk;
import Lab_1.music.ClassicalMusic;
import Lab_1.music.Music;
import Lab_1.music.PopMusic;
import Lab_1.music.RockMusic;
import Lab_1.music.util.MusicType;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordDiskTest {

    @Test
    public void shouldAddMusic() {
        Music classicalMusic = new ClassicalMusic(1000, "Classical music");
        RecordDisk recordDisk = new RecordDisk(classicalMusic);

        Music popMusic = new PopMusic(1500, "Pop music");
        recordDisk.addMusic(popMusic);

        assertThat(recordDisk.diskSize()).isEqualTo(2);
        assertThat(recordDisk.getRecords().get(0)).isEqualTo(classicalMusic);
        assertThat(recordDisk.getRecords().get(1)).isEqualTo(popMusic);
    }

    @Test
    public void shouldRemoveMusic() {
        Music classicalMusic = new ClassicalMusic(1000, "Classical music");
        Music popMusic = new PopMusic(1500, "Pop music");

        RecordDisk recordDisk = new RecordDisk(classicalMusic, popMusic);

        recordDisk.removeMusic(classicalMusic);

        assertThat(recordDisk.diskSize()).isEqualTo(1);
        assertThat(recordDisk.getRecords().get(0)).isEqualTo(popMusic);
    }

    @Test
    public void shouldSortByMusicTypeAsc() {
        RecordDisk recordDisk = new RecordDisk();

        Music rockMusic = new RockMusic(2000, "Rock music");
        Music popMusic = new PopMusic(1500, "Pop music");
        Music classicalMusic = new ClassicalMusic(1000, "Classical music");

        recordDisk.addMusic(popMusic);
        recordDisk.addMusic(rockMusic);
        recordDisk.addMusic(classicalMusic);

        // sort asc like this: classical, pop, rock
        recordDisk.sortByMusicType(Comparator.comparing(MusicType::ordinal));

        assertThat(recordDisk.getRecords().get(0)).isEqualTo(classicalMusic);
        assertThat(recordDisk.getRecords().get(1)).isEqualTo(rockMusic);
        assertThat(recordDisk.getRecords().get(2)).isEqualTo(popMusic);
    }

    @Test
    public void shouldSortByMusicTypeDesc() {
        RecordDisk recordDisk = new RecordDisk();

        Music classicalMusic = new ClassicalMusic(1000, "Classical music");
        Music popMusic = new PopMusic(1500, "Pop music");
        Music rockMusic = new RockMusic(2000, "Rock music");

        recordDisk.addMusic(classicalMusic);
        recordDisk.addMusic(popMusic);
        recordDisk.addMusic(rockMusic);

        // sort desc like this: rock, pop, classical
        recordDisk.sortByMusicType(Comparator.comparing(MusicType::ordinal).reversed());

        assertThat(recordDisk.getRecords().get(0)).isEqualTo(popMusic);
        assertThat(recordDisk.getRecords().get(1)).isEqualTo(rockMusic);
        assertThat(recordDisk.getRecords().get(2)).isEqualTo(classicalMusic);
    }

    @Test
    public void shouldFindDurationBetween() {
        Music classicalMusic = new ClassicalMusic(1000, "Classical music");
        Music popMusic = new PopMusic(1500, "Pop music");
        Music rockMusic = new RockMusic(2000, "Rock music");

        RecordDisk recordDisk = new RecordDisk(classicalMusic, popMusic, rockMusic);

        List<Music> durationBetween = recordDisk.findDurationBetween(1400, 1800);

        assertThat(durationBetween.size()).isEqualTo(1);
        assertThat(durationBetween.get(0)).isEqualTo(popMusic);
    }
}
