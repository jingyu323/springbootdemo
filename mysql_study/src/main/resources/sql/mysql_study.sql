# 分区改造


# 增加分区
ALTER TABLE test_user
    PARTITION BY RANGE (id) (
        PARTITION p0 VALUES LESS THAN (100),
        PARTITION p1 VALUES LESS THAN (200),
        PARTITION p2 VALUES LESS THAN (300),
        PARTITION pN VALUES LESS THAN (MAXVALUE)
        );

# 查询
select *
from test_user partition (pn);

#删除分区的同时会把数据同时清理
alter table test_user
    drop partition p0;

#修改分区


# 修改maxvalue ，添加新的分区
ALTER TABLE test_user
    REORGANIZE PARTITION default_part
        INTO (PARTITION p3 VALUES LESS THAN (400)
        , PARTITION default_part VALUES LESS THAN MAXVALUE);


## 修改pN为default_part
ALTER TABLE test_user
    REORGANIZE PARTITION pN
        INTO (PARTITION p5 VALUES LESS THAN (600)
        , PARTITION default_part VALUES LESS THAN MAXVALUE);
#合并分区
alter table user
    reorganize partition p0,p1,p2,p3 into
        (partition p02 values less than (12));

## 查询有缺陷，之后创建的分区不会把记录统计进去，查询处理的心分区记录为0

SELECT PARTITION_NAME AS '分区名',
       TABLE_ROWS     AS '记录数'
FROM information_schema.PARTITIONS

WHERE table_schema = 'mydatabase'
  AND table_name = 'test_user';


# 定时添加分区

# 查看定时任务是否开启
show variables like '%event_sche%';

# 创建分区存储过程
DROP PROCEDURE IF EXISTS add_partition;
DELIMITER //

CREATE PROCEDURE add_partition()
BEGIN
    DECLARE max_pd_num INT;
    DECLARE max_pd_name varchar(32);
    DECLARE netxt_pation INT;
    DECLARE netxt_pation_name varchar(32);

    DECLARE cur_test1 CURSOR FOR
        SELECT max(PARTITION_NAME)        as 'max_pd_name',
               max(partition_description) as 'max_pd_num'
        FROM information_schema.PARTITIONS

        WHERE table_schema = 'mydatabase'
          AND table_name = 'test_user'
          AND PARTITION_NAME like "p%";

    OPEN cur_test1;

    FETCH cur_test1 INTO max_pd_name,max_pd_num;

    SELECT max_pd_name, max_pd_num;

    CLOSE cur_test1;
-- 	 设置值
    set netxt_pation := max_pd_num + 100;
    SELECT netxt_pation, max_pd_name;
    set netxt_pation_name = CONCAT('p', REPLACE(max_pd_name, 'p', '') + 1);

    SELECT netxt_pation_name;

    SET @sql = CONCAT('ALTER TABLE test_user
			REORGANIZE PARTITION default_part
        INTO (PARTITION ', netxt_pation_name, ' VALUES LESS THAN (', netxt_pation, ')
        , PARTITION default_part VALUES LESS THAN MAXVALUE);');

    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //

DELIMITER ;


call add_partition();