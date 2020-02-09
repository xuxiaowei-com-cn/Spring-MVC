# Redis 安装

## [单节点](https://xuxiaowei.blog.csdn.net/article/details/103964264)

## [主从复制](https://xuxiaowei.blog.csdn.net/article/details/103964991)

## [集群配置](https://xuxiaowei.blog.csdn.net/article/details/104239333)

# Redis 说明

- 在 redis 安装目录中创建 cluster 文件夹

~~~
mkdir cluster
~~~

- 进入 cluster 文件夹

~~~
cd cluster
~~~

## 以下所有操作都是在 cluster 文件夹中

## 集群脚本

- 参见本目录中的 redis-6001-6006.conf

# 启动脚本

## 创建

~~~
vim start-all.sh
~~~

## 脚本内容

~~~
../src/redis-server redis-6001.conf
../src/redis-server redis-6002.conf
../src/redis-server redis-6003.conf
../src/redis-server redis-6004.conf
../src/redis-server redis-6005.conf
../src/redis-server redis-6006.conf
~~~

## 脚本授权

~~~
chmod +x start-all.sh
~~~

# 停止脚本

## 创建

~~~
vim stop-all.sh
~~~

## 脚本内容

~~~
../src/redis-cli -p 6001 shutdown
../src/redis-cli -p 6002 shutdown
../src/redis-cli -p 6003 shutdown
../src/redis-cli -p 6004 shutdown
../src/redis-cli -p 6005 shutdown
../src/redis-cli -p 6006 shutdown
~~~

## 脚本授权

~~~
chmod +x stop-all.sh
~~~

# 创建集群

- 使用 Redis 所在服务器的 IP
~~~
../src/redis-cli --cluster create --cluster-replicas 1 192.168.8.129:6001 192.168.8.129:6002 192.168.8.129:6003 192.168.8.129:6004 192.168.8.129:6005 192.168.8.129:6006
~~~

- 提示：
~~~
Can I set the above configuration? (type 'yes' to accept):
~~~

- 输入：
~~~
yes
~~~

# 端口

## 开通端口

~~~
firewall-cmd --zone=public --add-port=6001/tcp --permanent
firewall-cmd --zone=public --add-port=6002/tcp --permanent
firewall-cmd --zone=public --add-port=6003/tcp --permanent
firewall-cmd --zone=public --add-port=6004/tcp --permanent
firewall-cmd --zone=public --add-port=6005/tcp --permanent
firewall-cmd --zone=public --add-port=6006/tcp --permanent
~~~

## 重新加载防火墙

~~~
firewall-cmd --reload
~~~

## 查看已开放的端口与服务
~~~
firewall-cmd --list-all
~~~

# 查看集群状态

~~~
../src/redis-cli --cluster info 127.0.0.1:6001
~~~

# 