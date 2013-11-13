package randomnaja.aismusic;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;

@Path("/download.html")
@Produces(MediaType.APPLICATION_OCTET_STREAM)
public class DownloadSongResource {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final ExternalServiceResource externalServiceResource;

    public DownloadSongResource(ExternalServiceResource externalServiceResource) {
        this.externalServiceResource = externalServiceResource;
    }

    @POST
    @CacheControl(mustRevalidate = true, noCache = true)
    public Response download(
            @FormParam("gmmdCode") String gmmdCode,
            @FormParam("cpId") String cpId,
            @FormParam("fileName") String fileName) throws IOException {

        File downloadedFile = externalServiceResource.downloadSong(gmmdCode, cpId);

        log.info("Got downloaded file = {}", downloadedFile);

        return Response.ok(downloadedFile).header("Content-Disposition", "inline; filename=" + fileName).build();
    }

}
