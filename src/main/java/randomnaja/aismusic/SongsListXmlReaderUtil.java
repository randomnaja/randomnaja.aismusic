package randomnaja.aismusic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.io.IOUtils;
import randomnaja.aismusic.xmlpojo.Content;

import java.io.FileInputStream;
import java.util.List;

public final class SongsListXmlReaderUtil {

    private SongsListXmlReaderUtil() {
        //intention
    }

    public static List<Content> deSerializeXmlSongsList(String xml) throws Exception {
        ObjectMapper mapper = new XmlMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TypeReference<List<Content>> typeRef = new TypeReference<List<Content>>() {};

        return mapper.readValue(xml, typeRef);
    }

    public static void main(String[] args) throws Exception {
        String xml = IOUtils.toString(new FileInputStream("/home/tone/Desktop/AISMusic/searchres.xml"));

        List<Content> contents = deSerializeXmlSongsList(xml);

        for (Content each : contents) {
            System.out.println(each.getArtistTH());
        }
    }
}
