package Lab_1;

import Lab_1.disk.RecordDisk;
import Lab_1.music.ClassicalMusic;
import Lab_1.music.Music;
import Lab_1.music.PopMusic;
import Lab_1.music.RockMusic;
import Lab_1.music.util.MusicType;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Music pop1 = new PopMusic(3200, "Blinding Lights");
        Music pop2 = new PopMusic(2855, "Dancing on My Own");

        Music rock1 = new RockMusic(3670, "Thunderstruck");
        Music rock2 = new RockMusic(4500, "Du hast");

        Music classical1 = new ClassicalMusic(3600, "Symphony No. 5");
        Music classical2 = new ClassicalMusic(3250, "Flight of the Bumblebee");

        // Create 2 record disks with different tracks
        RecordDisk disk1 = new RecordDisk(pop1, rock1, classical1, classical2);
        RecordDisk disk2 = new RecordDisk(pop2, rock1, rock2, classical1);

        System.out.println("Disk 1: " + disk1.getMusicRecordsNames());
        System.out.println("Disk 2: " + disk2.getMusicRecordsNames());
        System.out.println("--------------------");

        // Sort tracks by music type
        disk1.sortByMusicType(Comparator.comparing(MusicType::ordinal));
        disk2.sortByMusicType(Comparator.comparing(MusicType::ordinal).reversed());

        System.out.println("Disk 1: " + disk1.getMusicRecordsNames());
        System.out.println("Disk 2: " + disk2.getMusicRecordsNames());
        System.out.println("--------------------");

        // Find tracks by duration
        List<Music> byDurationDisk1 = disk1.findDurationBetween(3300, 4000);
        List<Music> byDurationDisk2 = disk2.findDurationBetween(2700, 3400);

        System.out.println("Disk 1: " + byDurationDisk1);
        System.out.println("Disk 2: " + byDurationDisk2);
        System.out.println("--------------------");
    }
}
