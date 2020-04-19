https://dev.mysql.com/doc/refman/8.0/en/linux-installation-yum-repo.html#yum-repo-installing-mysql

```bash
# yum localinstall mysql80-community-release-el7-{version-number}.noarch.rpm
```

```bash
# yum install mysql-community-server
```
CentOS 7+
```bash
# systemctl status mysqld
```

```bash
# systemctl start mysqld
```

首次启动自动初始化并生成root用户的密码，用下面指令获取到 L)pv6%O#=i>o
```bash
# grep 'temporary password' /var/log/mysqld.log
```
```
[root@iZ2zecc7pels0cmno3yywuZ var]# grep 'temporary password' /var/log/mysqld.log
2020-04-17T15:57:30.722809Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: L)pv6%O#=i>o
```
修改密码，不能太简单！
```mysql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'aNewPassword'
```