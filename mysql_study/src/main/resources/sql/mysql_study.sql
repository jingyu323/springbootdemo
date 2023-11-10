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

#删除分区
alter table test_user
    drop partition p0;

#修改分区


# 修改maxvalue ，添加新的分区
ALTER TABLE test_user
    REORGANIZE PARTITION pN
        INTO (PARTITION p3 VALUES LESS THAN (400)
        , PARTITION pN VALUES LESS THAN MAXVALUE);

##

SELECT PARTITION_NAME AS '分区名',
       TABLE_ROWS     AS '记录数'
FROM information_schema.PARTITIONS

WHERE table_schema = 'mydatabase'
  AND table_name = 'test_user';


# 定时添加分区

show variables like '%event_sche%';

DELIMITER //

CREATE PROCEDURE add_partition()
BEGIN
    DECLARE max_year INT;
    DECLARE current_year INT DEFAULT YEAR(CURDATE());

    SELECT MAX(YEAR(date)) INTO max_year FROM old_table;

    IF max_year < current_year THEN
        SET max_year = current_year;
    END IF;

    SET @sql = CONCAT('ALTER TABLE old_table ADD PARTITION (PARTITION p', max_year + 1, ' VALUES LESS THAN (',
                      max_year + 1, '))');

    PREPARE stmt FROM @sql;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END //

DELIMITER ; 