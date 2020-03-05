package com.hosiang.dev.dao;

import com.hosiang.dev.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public interface OrderRepository extends JpaRepository<Order, String> {

    //获取 图表订单数量 数据 start
    @Query(value =
            "select B.id as hour,IFNULL(A.count,0) as count from (\n" +
            "  select count(B.pname) as count,C.`name` as cname , date_format(create_time,'%H') as hour from store A\n" +
            "  left join products B on A.product_id=B.id\n" +
            "  left join product_category_default C on B.category_id=C.id\n" +
            "  where 1=1\n" +
            "  and to_days(create_time) = to_days(now())\n" +
            "  and B.company_id = :companyId \n" +
            "  and C.`name` !=''\n" +
            "  group by cname\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<24\n" +
            ") B\n" +
            "on A.hour = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getCompanyOrderTotalToday(@Param("companyId") String cId);
    @Query(value =
            "select B.id as hour,IFNULL(A.count,0) as count from (\n" +
            "  select count(B.pname) as count,C.`name` as cname , date_format(create_time,'%H') as hour from store A\n" +
            "  left join products B on A.product_id=B.id\n" +
            "  left join product_category_default C on B.category_id=C.id\n" +
            "  where 1=1\n" +
            "  and to_days(create_time) = to_days(now())\n" +
            "  and B.company_id = :companyId \n" +
            "  and C.`name` !=''\n" +
            "  group by cname\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<24\n" +
            ") B\n" +
            "on A.hour = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getCompanyOrderTotalWeekly(@Param("companyId") String cId);
    @Query(value =
            "select B.id as day,IFNULL(A.count,0) as count from (\n" +
            "  SELECT count(id) as count, date_format(create_time,'%d') as day FROM company_orders\n" +
            "  WHERE 1=1\n" +
            "  and DATE_FORMAT( create_time, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m')\n" +
            "  and company_id=:companyId\n" +
            "  group by DATE_FORMAT( create_time, '%d')\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<day(LAST_DAY(CURDATE()))\n" +
            ") B\n" +
            "on A.day = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getCompanyOrderTotalMonthly(@Param("companyId") String cId);
    //获取 图表订单数量 数据 end

    //获取 图表订单金额 数据 start
    @Query(value =
            "select B.id as hour,IFNULL(A.count,0) as count from (\n" +
            "  SELECT sum(money) as count, date_format(create_time,'%H') as hour FROM company_orders\n" +
            "  WHERE 1=1\n" +
            "  and to_days(create_time) = to_days(now())\n" +
            "  and company_id=:companyId\n" +
            "  group by DATE_FORMAT( create_time, '%H')\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<24\n" +
            ") B\n" +
            "on A.hour = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getCompanyOrderMoneyToday(@Param("companyId") String cId);
    @Query(value =
            "select B.id as day,IFNULL(A.count,0) as count from (\n" +
            "  SELECT sum(money) as count, date_format(create_time,'%w') as day FROM company_orders\n" +
            "  WHERE 1=1\n" +
            "  and YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())\n" +
            "  and company_id=:companyId\n" +
            "  group by DATE_FORMAT( create_time, '%w')\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<7\n" +
            ") B\n" +
            "on A.day = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getCompanyOrderMoneyWeekly(@Param("companyId") String cId);
    @Query(value =
            "select B.id as day,IFNULL(A.count,0) as count from (\n" +
            "  SELECT sum(money) as count, date_format(create_time,'%d') as day FROM company_orders\n" +
            "  WHERE 1=1\n" +
            "  and DATE_FORMAT( create_time, '%Y%m' ) = DATE_FORMAT( CURDATE() , '%Y%m')\n" +
            "  and company_id=:companyId\n" +
            "  group by DATE_FORMAT( create_time, '%d')\n" +
            ") A\n" +
            "right join (\n" +
            "  select substring_index(substring_index('1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31',',',help_topic_id+1),',',-1) as id from mysql.help_topic where help_topic_id<day(LAST_DAY(CURDATE()))\n" +
            ") B\n" +
            "on A.day = B.id\n" +
            "order by convert(B.id,signed) asc", nativeQuery = true)
    List<Map<String, Object>> getCompanyOrderMoneyMonthly(@Param("companyId") String cId);
    //获取 图表订单金额 数据 end
}
