package org.prototypeDatabase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Peyppicp on 2016/9/6.
 */
public class PrototypeDatabaseConfiguration {

    private String base_path;
    private File base_file;

    public PrototypeDatabaseConfiguration() throws IOException {

        Properties properties = new Properties();
        String properties_path = System.getProperty("user.dir") + "\\src\\PDatabase.properties";
        properties.load(new BufferedInputStream(new FileInputStream(properties_path)));
        this.base_path = properties.getProperty("path");
        this.base_file = new File(base_path);
    }

    public String getBase_path() {
        return base_path;
    }

    public void setBase_path(String base_path) {
        this.base_path = base_path;
    }

    public File getBase_file() {
        return base_file;
    }

    public void setBase_file(File base_file) {
        this.base_file = base_file;
    }
}
