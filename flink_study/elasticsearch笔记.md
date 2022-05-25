# 安装
由于elasticsearch不能使用root账户启动，所以需要创建账户
#创建用户组
groupadd es
#创建用户
useradd -g es snail_es
#授权
chown -R snail_es.es /usr/local/elasticsearch/



mkdir /usr/local/es
chown -R snail_es.es /usr/local/es


# 切换到root用户
su root 

#1. ===最大可创建文件数太小=======
vim /etc/security/limits.conf 
# 在文件末尾中增加下面内容
snail_es soft nofile 65536
snail_es hard nofile 65536
# =====
vim /etc/security/limits.d/20-nproc.conf
# 在文件末尾中增加下面内容
snail_es soft nofile 65536
snail_es hard nofile 65536
*  hard    nproc     4096
# 注：* 代表Linux所有用户名称	

#2. ===最大虚拟内存太小=======
vim /etc/sysctl.conf
# 在文件中增加下面内容
vm.max_map_count=655360
# 重新加载，输入下面命令：
sysctl -p
