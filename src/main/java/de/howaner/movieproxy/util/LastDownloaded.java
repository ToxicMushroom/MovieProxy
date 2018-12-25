package de.howaner.movieproxy.util;

public class LastDownloaded {
    private static LastDownloaded lastDownloaded = new LastDownloaded();

    private String series = "";
    private String season = "";
    private String episode = "";

    public LastDownloaded() {

    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getEpisode() {
        return episode;
    }

    public String getSeason() {
        return season;
    }

    public String getSeries() {
        return series;
    }

    public static LastDownloaded getLastDownloaded() {
        return lastDownloaded;
    }
}
