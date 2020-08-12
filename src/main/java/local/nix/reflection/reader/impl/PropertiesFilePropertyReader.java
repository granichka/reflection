package local.nix.reflection.reader.impl;

import local.nix.reflection.reader.PropertyReader;
import local.nix.reflection.util.ResourceUtil;

import java.util.Map;

public class PropertiesFilePropertyReader implements PropertyReader {


    public Map<String, String> readProperties(String nameOfPropertiesFile) {

        Map<String, String> properties = ResourceUtil.getResource(nameOfPropertiesFile);
        return properties;
    }
}
