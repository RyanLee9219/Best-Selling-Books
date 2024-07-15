package system.book;

import java.util.HashSet;
import java.util.Set;

public class BookLanguageSet {
    private Set<String> languageSet = new HashSet<>();

    public Set<String> getLanguageSet() {
        return languageSet;
    }

    public void addLanguage(String language) {
        languageSet.add(language);
    }
}