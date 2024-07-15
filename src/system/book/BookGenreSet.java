package system.book;

import java.util.HashSet;
import java.util.Set;

public class BookGenreSet {
    private Set<String> genreSet = new HashSet<>();

    public Set<String> getGenreSet() {
        return genreSet;
    }

    public void addGenre(String genre) {
        genreSet.add(genre);
    }
}



