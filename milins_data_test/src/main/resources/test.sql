


    CREATE TABLE device
    (
        dev_id               INT NOT NULL AUTO_INCREMENT COMMENT '设备ID',
        dev_code             VARCHAR(64) COMMENT '设备编号',
        dev_state            VARCHAR(4) COMMENT '设备状态',
        last_online          DATETIME COMMENT '最后一次上线时间',
        firmware_version     VARCHAR(64) COMMENT '固件版本号',
        disk_total           DOUBLE COMMENT '硬盘总容量',
        disk_used            DOUBLE COMMENT '硬盘已用容量',
        create_time          DATETIME COMMENT '创建时间',
        create_user          INT COMMENT '创建用户',
        update_time          DATETIME COMMENT '更新时间',
        update_user          INT COMMENT '更新用户',
        flag                 VARCHAR(1) COMMENT '删除标识',
        PRIMARY KEY (dev_id)
    )
        DEFAULT CHARACTER SET = utf8
        COLLATE = utf8_unicode_ci;

    ALTER TABLE device COMMENT '网关设备表';


    CREATE TABLE train
    (
        train_id             INT NOT NULL AUTO_INCREMENT COMMENT '机车id',

        create_time          DATETIME COMMENT '创建时间',
        create_user          INT COMMENT '创建用户',
        update_time          DATETIME COMMENT '更新时间',
        update_user          INT COMMENT '更新用户',
        flag                 VARCHAR(1) COMMENT '删除标识',
        PRIMARY KEY (train_id)
    )
        DEFAULT CHARACTER SET = utf8
        COLLATE = utf8_unicode_ci;


    CREATE TABLE train_device
    (
        train_code           VARCHAR(64) NOT NULL COMMENT '机车编码',
        dev_code             VARCHAR(64) NOT NULL COMMENT '网关编码',
        PRIMARY KEY (train_code, dev_code)
    )
        DEFAULT CHARACTER SET = utf8
        COLLATE = utf8_unicode_ci;

    ALTER TABLE train_device COMMENT '机车绑定网关表';


    ALTER TABLE `device`
        ADD INDEX device (`dev_code`,`last_online`);