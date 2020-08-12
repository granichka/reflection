package local.nix.reflection.reader;

import java.util.Map;

public interface PropertyReader {

    Map<String, String> readProperties(String nameOfSource);
}
