package Lab_1.music;

import Lab_1.music.util.MusicType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public abstract class Music {
    private final int duration;
    private final String name;

    public abstract MusicType getType();

    @Override
    public String toString() {
        return "{" +
                "duration = " + duration +
                ", name = '" + name + '\'' +
                '}';
    }
}
