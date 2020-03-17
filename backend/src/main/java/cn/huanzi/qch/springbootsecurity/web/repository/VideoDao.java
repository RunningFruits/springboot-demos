package cn.huanzi.qch.springbootsecurity.web.repository;

import cn.huanzi.qch.springbootsecurity.web.pojo.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDao extends JpaRepository<Video, Long> {

    @Query(value = "select * from t_video where id=#{id} limit 1", nativeQuery = true)
    Video getVideoById(Integer id);

    @Query(value = "select * from t_video", nativeQuery = true)
    List<Video> getVideoList();

}
