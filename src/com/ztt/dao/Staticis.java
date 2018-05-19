package com.ztt.dao;

import com.ztt.bean.*;
import com.ztt.util.Caculate_similarity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zhaotian
 * @date 2018/4/28 15:22
 */
@Repository("staticisDao")
@Transactional
public class Staticis implements IStaticis {
    @Autowired
    private SessionFactory sessionFactory;
    @Resource
    private IUserDao userDao;
    @Resource
    private IMusicDao musicDao;

    @Override
    public void add_user_music_data(User_Music_Data_Statistics user_music_data_statistics) {
        sessionFactory.getCurrentSession().save(user_music_data_statistics);
    }

    @Override
    public void add_user_user_similarity(Two_user_similarity two_user_similarity) {
//        System.out.println("添加"+two_user_similarity.getDouble_user_id().getUser_1_id()+"=="+two_user_similarity.getDouble_user_id().getUser_2_id()+"===="+two_user_similarity.getSimilarity());
        sessionFactory.getCurrentSession().save(two_user_similarity);
    }

    @Override
    public void add_user_recommand_music_list(Recommand_musics recommand_musics) {
        sessionFactory.getCurrentSession().save(recommand_musics);
    }

    @Override
    public void DeleteAll() {//清楚所有的推荐相关信息，便于后期的重新生成
        String sql1 = "DELETE FROM user_music_data_statistics";
        String sql2 = "DELETE FROM user_similarity";
        String sql3 = "DELETE FROM recommand_song";
        String sql4 = "DELETE FROM recommand_song_list";
        sessionFactory.getCurrentSession().createSQLQuery(sql1).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery(sql2).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery(sql3).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery(sql4).executeUpdate();
    }

    @Override
    public User_music_score get_user_music_score(String user_id) {
        LinkedHashMap<String, Double> music_score = new LinkedHashMap<String, Double>();
        String hql = "from User_Music_Data_Statistics u where u.doubleId.user_id=:user_id order by u.score desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("user_id", user_id);
        ArrayList<User_Music_Data_Statistics> list = (ArrayList<User_Music_Data_Statistics>) query.list();
        for (User_Music_Data_Statistics u : list) {
            music_score.put(u.getDoubleId().getMusic_id(), u.getScore());
        }
        return new User_music_score(user_id, music_score);
    }

    @Override
    public User_music_score get_select_user_music_score(String user_id) {
        LinkedHashMap<String, Double> music_score = new LinkedHashMap<String, Double>();
        //筛选评分大于等于1.5的歌曲
        String hql = "from User_Music_Data_Statistics u where u.doubleId.user_id=:user_id and u.score >= 1.5 order by u.score desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("user_id", user_id);
        ArrayList<User_Music_Data_Statistics> list = (ArrayList<User_Music_Data_Statistics>) query.list();
        for (User_Music_Data_Statistics u : list) {
            music_score.put(u.getDoubleId().getMusic_id(), u.getScore());
        }
        return new User_music_score(user_id, music_score);
    }

    @Override
    public ArrayList<User_music_score> get_all_user_music_score() {
        ArrayList<User_music_score> user_music_scores = new ArrayList<User_music_score>();
        ArrayList<User> users = userDao.getAlluser();
        for (User user : users) {
            user_music_scores.add(get_user_music_score(user.getId()));
        }
        return user_music_scores;
    }

    @Override
    public Two_user_similarity get_two_user_similarity(User_music_score user_music_score1, User_music_score user_music_score2) {
        Two_user_similarity two_user_similarity;
        LinkedHashMap<String, Double> user1_music_scores = user_music_score1.getMusic_score();
        LinkedHashMap<String, Double> user2_music_scores = user_music_score2.getMusic_score();
        Iterator iterator1 = user1_music_scores.keySet().iterator();
        Double all_common_music_similarity = 0.0;
        int common_music_num = 0;
        while (iterator1.hasNext()) {
            String music_id = (String) iterator1.next();
            Double score1 = user1_music_scores.get(music_id);
            if (user2_music_scores.containsKey(music_id)) {
                common_music_num = common_music_num + 1;
                all_common_music_similarity += Caculate_similarity.cos(score1, user2_music_scores.get(music_id));
                System.out.println(score1 + "===" + user2_music_scores.get(music_id));
            }
        }
        if (common_music_num == 0) {
            two_user_similarity = new Two_user_similarity(new Double_User_Id(user_music_score1.getUser_id(), user_music_score2.getUser_id()), 0);
            return two_user_similarity;
        }
        // TODO: 2018/5/11 自定义规则 
        two_user_similarity = new Two_user_similarity(new Double_User_Id(user_music_score1.getUser_id(), user_music_score2.getUser_id()), 0.8 * all_common_music_similarity / common_music_num + 0.2 * common_music_num / user1_music_scores.size());
        return two_user_similarity;
    }

    @Override
    public ArrayList<Two_user_similarity> get_all_users_similarity() {
        ArrayList<Two_user_similarity> two_user_similarities = new ArrayList<>();
        ArrayList<User_music_score> user_music_scores = get_all_user_music_score();
        int length = user_music_scores.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                two_user_similarities.add(get_two_user_similarity(user_music_scores.get(i), user_music_scores.get(j)));
            }
        }
        return two_user_similarities;
    }

    @Override
    public ArrayList<String> get_similarity_order_desc(String user_id) {
        String hql = "select double_user_id.user_2_id from Two_user_similarity where double_user_id.user_1_id=:user_id and similarity!=0 order by similarity desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("user_id", user_id);
        ArrayList<String> user_2_id = (ArrayList<String>) query.list();
        for (String x : user_2_id) {
            System.out.println(x + "===" + userDao.getUserById(x).getName());
        }
        return user_2_id;
    }

    @Override
    public ArrayList<Song> get_one_recomand_music_top_30(String user_id) {
        ArrayList<String> similary_user_ids = get_similarity_order_desc(user_id);
        ArrayList<Song> songs = new ArrayList<Song>();
        int recommand_nums = 0;//推荐歌曲数量计数器
        LinkedHashMap<String, Double> user_music_score_main = get_user_music_score(user_id).getMusic_score();
        w:
        for (String u_id : similary_user_ids) {
            User_music_score user_music_score = get_select_user_music_score(u_id);
            LinkedHashMap<String, Double> user_music_score_other = user_music_score.getMusic_score();
            n:
            for (String song_id : user_music_score_other.keySet()) {
                System.out.print(user_music_score_other.get(song_id));
                if (recommand_nums == 30) {
                    break w;
                }
                if (!user_music_score_main.containsKey(song_id)) {//判断主体是否也听过
                    songs.add(musicDao.getMusicById(song_id));
                    recommand_nums = recommand_nums + 1;
                }
            }
        }
        if (recommand_nums < 30) {
            //当用户没有足够多的相似用户带来的歌曲推荐使用播放排行榜，这个一般出现在新注册用户且没有听歌，收藏，下载等行为。
            for (Song song : musicDao.Play_Top()) {
                if (!songs.contains(song)&&!user_music_score_main.containsKey(song.getSong_id())) {//判断主体是否也听过
                    songs.add(song);
                    recommand_nums = recommand_nums + 1;
                }
                if (recommand_nums == 30) {
                    break;
                }
            }
        }
        System.out.println(songs.size());
        return songs;
    }

    @Override
    public LinkedHashMap<User, ArrayList<Song>> get_all_recomand_music_top_30() {
        LinkedHashMap<User, ArrayList<Song>> user_recommand_muscilist = new LinkedHashMap<User, ArrayList<Song>>();
        ArrayList<User> users = userDao.getAlluser();
        for (User user : users) {
            ArrayList<Song> user_recommand_muscis = get_one_recomand_music_top_30(user.getId());
            user_recommand_muscilist.put(user, user_recommand_muscis);
        }
        return user_recommand_muscilist;
    }
}
