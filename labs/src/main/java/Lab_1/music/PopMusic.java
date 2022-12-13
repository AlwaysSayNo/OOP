package Lab_1.music;

import Lab_1.music.Music;
import Lab_1.music.util.MusicType;

public class PopMusic extends Music {
    public PopMusic(int duration, String name) {
        super(duration, name);
    }

    public MusicType getType() {
        return MusicType.POP;
    }
}
