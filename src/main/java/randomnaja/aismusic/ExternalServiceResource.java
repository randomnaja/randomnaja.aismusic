package randomnaja.aismusic;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import randomnaja.aismusic.exception.GeneralAisMusicException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ExternalServiceResource {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final HttpClient httpClient;

    private final MainConfiguration config;

    public ExternalServiceResource(HttpClient httpClient, MainConfiguration config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    public String pingHttpBin() throws IOException {
        HttpGet get = new HttpGet("http://httpbin.org/get");

        try {
            HttpResponse res = httpClient.execute(get);
            return EntityUtils.toString(res.getEntity());
        } finally {
            get.releaseConnection();
        }
    }

    public String searchSong(String keyword) throws IOException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("directory.gmmwireless.com").setPath("/aismusic/api//searchContents.jsp")
                .setParameter("APP_ID", "65")
                //.setParameter("DEVICE", config.getDeviceNumber())
                .setParameter("CHARSET", "utf-8")
                //.setParameter("MSISDN", config.getMsisdn())
                .setParameter("APPVERSION", "4.1")
                .setParameter("APIVERSION", "5.0.0")
                .setParameter("REC", "20")
                .setParameter("PAGE", "1")
                .setParameter("SHELF_ID", "")
                .setParameter("KEY", keyword);

        URI uri = null;
        try {
            uri = builder.build();
        } catch (URISyntaxException e) {
            throw new GeneralAisMusicException(e);
        }

        log.info("Searching using URL = {}", uri);

        HttpGet get = new HttpGet(uri);

        try {
            HttpResponse res = httpClient.execute(get);

            String resXml = EntityUtils.toString(res.getEntity());

            log.debug("got resXml = {}", resXml);

            return resXml;
        } finally {
            get.releaseConnection();
        }
    }
}
