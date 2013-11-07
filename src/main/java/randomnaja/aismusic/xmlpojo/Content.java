package randomnaja.aismusic.xmlpojo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Content {

    @JacksonXmlProperty(localName = "ContentCode")
    private String contentCode;

    @JacksonXmlProperty(localName = "GMMDCode")
    private String gmmdCode;

    private String sFullSong;
    private String sVide;
    private String sKaraoke;
    private String sConcert;

    @JacksonXmlProperty(localName = "SongNameEN")
    private String songNameEN;

    @JacksonXmlProperty(localName = "SongNameTH")
    private String songNameTH;

    private String thmCover;
    private String cover;


    @JacksonXmlProperty(localName = "AlbumTH")
    private String AlbumTH;
    @JacksonXmlProperty(localName = "ArtistEN")
    private String ArtistEN;
    @JacksonXmlProperty(localName = "ArtistTH")
    private String ArtistTH;

    private String preview;
    private String sDuration;
    private String rbt;

    @JacksonXmlProperty(localName = "CP_ID")
    private String cpId;

    private String mvCover;
    private String karaokeCover;
    private String concertCover;

    @JacksonXmlProperty(localName = "streaming_mv")
    private String streaming_mv;
    @JacksonXmlProperty(localName = "streaming_fs")
    private String streaming_fs;

    private String lyrics;

    @JacksonXmlProperty(localName = "MEDIA_CODE")
    private String mediaCode;

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getGmmdCode() {
        return gmmdCode;
    }

    public void setGmmdCode(String gmmdCode) {
        this.gmmdCode = gmmdCode;
    }

    public String getsFullSong() {
        return sFullSong;
    }

    public void setsFullSong(String sFullSong) {
        this.sFullSong = sFullSong;
    }

    public String getsVide() {
        return sVide;
    }

    public void setsVide(String sVide) {
        this.sVide = sVide;
    }

    public String getsKaraoke() {
        return sKaraoke;
    }

    public void setsKaraoke(String sKaraoke) {
        this.sKaraoke = sKaraoke;
    }

    public String getsConcert() {
        return sConcert;
    }

    public void setsConcert(String sConcert) {
        this.sConcert = sConcert;
    }

    public String getSongNameEN() {
        return songNameEN;
    }

    public void setSongNameEN(String songNameEN) {
        this.songNameEN = songNameEN;
    }

    public String getSongNameTH() {
        return songNameTH;
    }

    public void setSongNameTH(String songNameTH) {
        this.songNameTH = songNameTH;
    }

    public String getThmCover() {
        return thmCover;
    }

    public void setThmCover(String thmCover) {
        this.thmCover = thmCover;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAlbumTH() {
        return AlbumTH;
    }

    public void setAlbumTH(String albumTH) {
        AlbumTH = albumTH;
    }

    public String getArtistEN() {
        return ArtistEN;
    }

    public void setArtistEN(String artistEN) {
        ArtistEN = artistEN;
    }

    public String getArtistTH() {
        return ArtistTH;
    }

    public void setArtistTH(String artistTH) {
        ArtistTH = artistTH;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getsDuration() {
        return sDuration;
    }

    public void setsDuration(String sDuration) {
        this.sDuration = sDuration;
    }

    public String getRbt() {
        return rbt;
    }

    public void setRbt(String rbt) {
        this.rbt = rbt;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getMvCover() {
        return mvCover;
    }

    public void setMvCover(String mvCover) {
        this.mvCover = mvCover;
    }

    public String getKaraokeCover() {
        return karaokeCover;
    }

    public void setKaraokeCover(String karaokeCover) {
        this.karaokeCover = karaokeCover;
    }

    public String getConcertCover() {
        return concertCover;
    }

    public void setConcertCover(String concertCover) {
        this.concertCover = concertCover;
    }

    public String getStreaming_mv() {
        return streaming_mv;
    }

    public void setStreaming_mv(String streaming_mv) {
        this.streaming_mv = streaming_mv;
    }

    public String getStreaming_fs() {
        return streaming_fs;
    }

    public void setStreaming_fs(String streaming_fs) {
        this.streaming_fs = streaming_fs;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getMediaCode() {
        return mediaCode;
    }

    public void setMediaCode(String mediaCode) {
        this.mediaCode = mediaCode;
    }
}
