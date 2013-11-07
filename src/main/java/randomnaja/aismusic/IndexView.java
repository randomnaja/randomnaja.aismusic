package randomnaja.aismusic;

import com.yammer.dropwizard.views.View;
import randomnaja.aismusic.xmlpojo.Content;

import java.nio.charset.Charset;
import java.util.List;

public class IndexView extends View {

    private final List<Content> contents;

    private final String keyword;

    protected IndexView(List<Content> contents, String keyword) {
        super("index.ftl", Charset.forName("UTF-8"));
        this.contents = contents;
        this.keyword = keyword;
    }

    public List<Content> getContents() {
        return contents;
    }

    public String getKeyword() {
        return keyword;
    }
}
