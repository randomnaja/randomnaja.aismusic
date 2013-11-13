package randomnaja.aismusic;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import randomnaja.aismusic.xmlpojo.Content;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/index.html")
@Produces(MediaType.TEXT_HTML)
public class MainPageResource {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final ExternalServiceResource externalServiceResource;

    public MainPageResource(ExternalServiceResource externalServiceResource) {
        this.externalServiceResource = externalServiceResource;
    }

    @GET
    @CacheControl(mustRevalidate = true, noCache = true)
    public View index(@QueryParam("keyword") String keyword) throws Exception {

        log.info("keyword = {}", keyword);

        if (keyword != null) {
            String xmlRes = externalServiceResource.searchSong(keyword);

            List<Content> contents = XmlReaderUtil.deSerializeXmlSongsList(xmlRes);

            return new IndexView(contents, keyword);
        }

        return new IndexView(null, null);
    }
}
