package randomnaja.aismusic.xmlpojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DownloadXmlResult {

    @JacksonXmlProperty(localName = "dl_link")
    private String dlLink;

    public String getDlLink() {
        return dlLink;
    }

    public void setDlLink(String dlLink) {
        this.dlLink = dlLink;
    }
}
