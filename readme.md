配置型多数据源

使用aop注解实现多数据源

mysql 用户
mac：
root
repl:repl slave连接用户
remote:remote spring远程用户
win：
root:395945

读写分离


network sub003

docker build -t jsenht/mbpt:1.0.1 .

docker run -d -p 8090:8080 --name mbpt --network=sub003 jsenht/mbpt:1.0.1
docker run -d -p 8080:8080 --name mbpt --network=sub003 jsenht/mbpt:1.0.1
