package de.howaner.movieproxy.util;


import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    private static Config config = new Config();
    private String configObject;
    private final File configFile = new File("config.json");

    public static Config getConfigInstance() {
        return config;
    }

    private Config() {
        if (!configFile.exists()) {
            create();
        }

        configObject = read(configFile);
    }

    private void create() {
        try {
            Files.write(Paths.get(configFile.getPath()), new Gson().toJson(LastDownloaded.getLastDownloaded()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String read(File file) {
        try {
            return Files.readString(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void configToCache() {
        LastDownloaded config = new Gson().fromJson(configObject, LastDownloaded.class);
        LastDownloaded cache = LastDownloaded.getLastDownloaded();
        cache.setSeries(config.getSeries());
        cache.setSeason(config.getSeason());
        cache.setEpisode(config.getEpisode());
    }

    public void saveCacheToConfig() {
        try {
            Files.write(Paths.get(configFile.getPath()), new Gson().toJson(LastDownloaded.getLastDownloaded()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
