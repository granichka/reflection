package local.nix.reflection;

import local.nix.reflection.data.AppProperties;
import local.nix.reflection.factory.impl.AppPropertiesFactory;
import local.nix.reflection.reader.impl.PropertiesFilePropertyReader;

public class Main {

    public static void main(String[] args) {

        AppProperties obj = new AppPropertiesFactory(new PropertiesFilePropertyReader(), "app.properties").getInstance();
        System.out.println(obj.databasePort);
        System.out.println(obj.isAdmin);
        System.out.println(obj.language);
        System.out.println(obj.maxConnections);
        System.out.println(obj.nameOfDatabase);
    }
}
