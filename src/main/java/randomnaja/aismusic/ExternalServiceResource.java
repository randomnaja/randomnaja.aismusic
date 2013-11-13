package randomnaja.aismusic;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import randomnaja.aismusic.exception.GeneralAisMusicException;
import randomnaja.aismusic.xmlpojo.DownloadXmlResult;

import java.io.File;
import java.io.FileOutputStream;
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

    /**
     * @return downloaded file, as a temp file, and shall be deleted afterward
     */
    public File downloadSong(String gmmdCode, String cpID) throws IOException {
        String xml = getSongDownloadLinkXml(gmmdCode, cpID);

        DownloadXmlResult downloadXmlResult;
        try {
            downloadXmlResult = XmlReaderUtil.deSerializeXmlDownloadSong(xml);
        } catch (Exception e) {
            throw new GeneralAisMusicException(e);
        }

        HttpGet get = new HttpGet(downloadXmlResult.getDlLink());

        try {
            HttpResponse resp = httpClient.execute(get);

            HttpEntity entity = resp.getEntity();
            long contentLength = entity.getContentLength();
            Header contentType = entity.getContentType();

            log.info("Downloading a song and got contentLength = {} and contentType = {}", contentLength, contentType);

            File tempFile = File.createTempFile(gmmdCode, "-temp-dl");
            FileOutputStream fout = new FileOutputStream(tempFile);

            entity.writeTo(fout);

            fout.flush();
            fout.close();

            return tempFile;

        } finally {
            get.releaseConnection();
        }
    }

    public String getSongDownloadLinkXml(String gmmdCode, String cpID) throws IOException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("directory.gmmwireless.com").setPath("/aismusic/api/download.jsp")
                .setParameter("APP_ID", "65")
                .setParameter("DEVICE", config.getDeviceNumber())
                .setParameter("CHARSET", "utf-8")
                .setParameter("GMMD_CODE", gmmdCode)
                .setParameter("SERVICE_ID", "1")
                .setParameter("MSISDN", config.getMsisdn())
                .setParameter("CP_ID", cpID)
                .setParameter("APPVERSION", "4.1")
                .setParameter("APIVERSION", "5.0.0")
                .setParameter("MEDIA_CODE", "1763")
                .setParameter("CONNECTION_TYPE", "WIFI")
                .setParameter("HD", "Y");

        URI uri = null;

        try {
            uri = builder.build();

            builder = new URIBuilder();
            builder.setScheme("http").setHost("musicstore.ais.co.th").setPath("/proxy.php")
                    .setParameter("url", uri.toString());

            uri = builder.build();
        } catch (URISyntaxException e) {
            throw new GeneralAisMusicException(e);
        }

        log.info("download using url = {}", uri);

        HttpGet get = new HttpGet(uri);

        try {
            HttpResponse res = httpClient.execute(get);

            HttpEntity entity = res.getEntity();
            Header type = entity.getContentType();
            long length = entity.getContentLength();

            log.info("Got response type = {}, length = {}", type, length);

            String xml = EntityUtils.toString(entity);

            return xml;
        } finally {
            get.releaseConnection();
        }
    }
}
