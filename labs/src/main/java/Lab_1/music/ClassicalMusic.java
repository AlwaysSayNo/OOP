package Lab_1.music;

import Lab_1.music.util.MusicType;

public class ClassicalMusic extends Music {
    public ClassicalMusic(int duration, String name) {
        super(duration, name);
    }

    public MusicType getType() {
        return MusicType.CLASSICAL;
    }
}
