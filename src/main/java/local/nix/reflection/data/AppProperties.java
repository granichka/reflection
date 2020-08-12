package local.nix.reflection.data;

import local.nix.reflection.annotation.PropertyKey;

public class AppProperties {

    @PropertyKey("app.language")
    public String language;

    @PropertyKey("connections.limit")
    public int maxConnections;

    @PropertyKey("database.name")
    public String nameOfDatabase;

    @PropertyKey("database.port")
    public String databasePort;

    @PropertyKey("role.admin")
    public boolean isAdmin;


}
