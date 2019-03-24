package com.example.lastfm;

import java.util.List;

public class ResultList {

    List<CommonResult> album;
    List<CommonResult> artist;
    List<CommonResult> track;

    public List<CommonResult> getAlbum() {
        return album;
    }

    public void setAlbum(List<CommonResult> album) {
        this.album = album;
    }

    public List<CommonResult> getArtist() {
        return artist;
    }

    public void setArtist(List<CommonResult> artist) {
        this.artist = artist;
    }

    public List<CommonResult> getTrack() {
        return track;
    }

    public void setTrack(List<CommonResult> track) {
        this.track = track;
    }
}
