package test.java.Lab_1;

import Lab_1.music.ClassicalMusic;
import Lab_1.music.Music;
import Lab_1.music.PopMusic;
import Lab_1.music.RockMusic;
import Lab_1.music.util.MusicType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MusicTest {

    @Test
    public void classicalMusicTypeShouldBeClassicalTest() {
        Music classicalMusic = new ClassicalMusic(1000, "Classical music");
        assertThat(classicalMusic.getType()).isEqualTo(MusicType.CLASSICAL);
    }

    @Test
    public void popMusicTypeShouldBePopTest() {
        Music popMusic = new PopMusic(1500, "Pop music");
        assertThat(popMusic.getType()).isEqualTo(MusicType.POP);
    }

    @Test
    public void rockMusicTypeShouldBeRockTest() {
        Music rockMusic = new RockMusic(2000, "Rock music");
        assertThat(rockMusic.getType()).isEqualTo(MusicType.ROCK);
    }
}
