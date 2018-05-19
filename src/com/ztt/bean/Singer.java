package com.ztt.bean;

import com.ztt.enumType.SingerType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/4/10 11:34
 */
@Entity
@Table(name = "singer")
public class Singer {
    private String singer_id;
    private String singer_name;
    private String singer_pic_url;
    private SingerType singerType;
    private List<Album> singer_albums;
    private List<Song>  singer_songs;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    public String getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(String singer_id) {
        this.singer_id = singer_id;
    }
    @Column(name = "singer_name",nullable = false,unique = true)
    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }
    @Column(name = "singer_pic_url")
    public String getSinger_pic_url() {
        return singer_pic_url;
    }

    public void setSinger_pic_url(String singer_pic_url) {
        this.singer_pic_url = singer_pic_url;
    }
    @Column(name="type")
    public SingerType getSingerType() {
        return singerType;
    }

    public void setSingerType(SingerType singerType) {
        this.singerType = singerType;
    }
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "album_author")
    public List<Album> getSinger_albums() {
        return singer_albums;
    }

    public void setSinger_albums(List<Album> singer_albums) {
        this.singer_albums = singer_albums;
    }
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "singer")
    public List<Song> getSinger_songs() {
        return singer_songs;
    }

    public void setSinger_songs(List<Song> singer_songs) {
        this.singer_songs = singer_songs;
    }
}
