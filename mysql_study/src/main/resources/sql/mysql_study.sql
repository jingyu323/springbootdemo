# 分区改造

# 增加分区
ALTER TABLE test_user
    PARTITION BY RANGE (id) (
        PARTITION p0 VALUES LESS THAN (100),
        PARTITION p1 VALUES LESS THAN (200),
        PARTITION p2 VALUES LESS THAN (300),
        PARTITION pN VALUES LESS THAN (MAXVALUE)
        );


#删除分区
alter table qfyu_chat
    drop partition p20221101;

#修改分区
alter table qfyu_chatadd partition (partition p20221101 values less than (738641));


# 定时添加分区