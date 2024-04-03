package algonquin.CST2335.pate1214;

public class Artist {
    public int id;
    public String name;
    public String tracklist; // URL to their songs

    // Constructor
    public Artist(int id, String name, String tracklist) {
        this.id = id;
        this.name = name;
        this.tracklist = tracklist;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTracklist() {
        return tracklist;
    }
}