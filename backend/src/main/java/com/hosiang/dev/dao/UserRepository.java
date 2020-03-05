package com.hosiang.dev.dao;

import com.hosiang.dev.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select B.id as month,IFNULL(A.count,0) as count from (\n" +
            "  SELECT count(*) as count, date_format(create_time,'%m') as month FROM user\n" +
            "  WHERE 1=1\n" +
            "  and DATE_FORMAT( create_time, '%Y' ) = DATE_FORMAT( CURDATE() , '%Y')\n" +
            "  group by DATE_FORMAT( create_time, '%Y%m')\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7,8,9,10,11,12',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<12\n" +
            ") B\n" +
            "on A.month = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getUserRegisterMonthly();
}
