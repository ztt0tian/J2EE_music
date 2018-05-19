package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/5/1 13:05
 */
@Entity
@Table(name="recommand_song_list")
public class Recommand_musics {
    private String recommand_id;
    private User user;
    private List<Song> recommand_songs;
    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "recommand_id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getRecommand_id() {
        return recommand_id;
    }

    public void setRecommand_id(String recommand_id) {
        this.recommand_id = recommand_id;
    }

    @OneToOne(optional = false,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany()
    @JoinTable(name = "recommand_song",joinColumns = {
            @JoinColumn(name = "recommand_id")
    },inverseJoinColumns = {@JoinColumn(name = "song_id")})
    public List<Song> getRecommand_songs() {
        return recommand_songs;
    }

    public void setRecommand_songs(List<Song> recommand_songs) {
        this.recommand_songs = recommand_songs;
    }

    public Recommand_musics() {
    }

    public Recommand_musics(User user, List<Song> recommand_songs) {
        this.user = user;
        this.recommand_songs = recommand_songs;
    }
}
