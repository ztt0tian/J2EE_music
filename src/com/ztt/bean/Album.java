package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/4/15 13:49
 */
@Entity
@Table(name = "album",uniqueConstraints = @UniqueConstraint(columnNames = {"album_name","singer_id"}))
public class Album {
    private String album_id;
    private String album_name;
    private Singer album_author;
    private List<Song> album_songs;
    private String album_pic_url;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    public String getAlbum_id() {
        return album_id;
    }
    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    @Column(name = "album_name",nullable = false)
    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "singer_id",nullable = false)
    public Singer getAlbum_author() {
        return album_author;
    }

    public void setAlbum_author(Singer album_author) {
        this.album_author = album_author;
    }
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "album")
    public List<Song> getAlbum_songs() {
        return album_songs;
    }

    public void setAlbum_songs(List<Song> album_songs) {
        this.album_songs = album_songs;
    }
    @Column(name = "album_pic_url")
    public String getAlbum_pic_url() {
        return album_pic_url;
    }

    public void setAlbum_pic_url(String album_pic_url) {
        this.album_pic_url = album_pic_url;
    }
}
