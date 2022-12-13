package Lab_1.music;

import Lab_1.music.Music;
import Lab_1.music.util.MusicType;

public class RockMusic extends Music {
    public RockMusic(int duration, String name) {
        super(duration, name);
    }

    public MusicType getType() {
        return MusicType.ROCK;
    }
}
