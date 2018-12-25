package de.howaner.movieproxy;

import com.google.gson.Gson;
import de.howaner.movieproxy.content.FileContentReceiverManager;
import de.howaner.movieproxy.download.DownloadManager;
import de.howaner.movieproxy.server.HttpServer;

import java.io.File;

import de.howaner.movieproxy.util.Config;
import de.howaner.movieproxy.util.LastDownloaded;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyApplication {
    @Getter
    private static ProxyApplication instance;

    @Getter
    private final DownloadManager downloadManager = new DownloadManager();
    @Getter
    private final FileContentReceiverManager fileContentReceiverManager = new FileContentReceiverManager();
    @Getter
    private final Logger logger = LoggerFactory.getLogger(ProxyApplication.class.getName());
    @Getter
    private final Gson gson;

    public ProxyApplication() {
        this.gson = new Gson();
    }

    public static void main(String[] args) {
        instance = new ProxyApplication();
        instance.start();
    }

    public File getStoragePath() {
        return new File("storage");
    }

    public File getCachePath() {
        return new File("cache");
    }

    private void start() {
        Config.getConfigInstance().configToCache();
        logger.info("-------------------------STARTING-------------------------");
        logger.info("Series: " + LastDownloaded.getLastDownloaded().getSeries());
        logger.info("Season: " + LastDownloaded.getLastDownloaded().getSeason());
        logger.info("Episode: " + LastDownloaded.getLastDownloaded().getEpisode());


        if (!this.getStoragePath().isDirectory())
            this.getStoragePath().mkdir();
        if (!this.getCachePath().isDirectory())
            this.getCachePath().mkdir();

        try {
            for (File file : this.getCachePath().listFiles()) {
                if (file.isFile())
                    file.delete();
            }
        } catch (Exception ignored) {}

        HttpServer server = new HttpServer();
        server.startServer();

        this.fileContentReceiverManager.start();

        // Wait until stop
        try {
            server.getServer().channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
