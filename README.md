# 软件配置
jdk8, maven3.2.3   

配置好java环境和maven私服地址   
# 系统目录
api-web:签名过滤，参数校验，返回数据封装。   

api-service:与各个rpc业务服务交互。   

api-common:一些公用的类和函数。   

# 打包
cd mxapi   

mvn clean install -Dmaven.test.skip=true   

在mxapi/api-web/target目录下，生成api-web-0.1-bin目录和api-web-0.1-bin.tar.gz包 ,api-web-0.1-bin.tar.就是由api-web-0.1-bin打包压缩而成。 

# 部署
cd api-web-0.1-bin\api-web-0.1\lib   

## windows 开发测试环境：   
start /b java-Djava.net.preferIPv4Stack=true -Djava.awt.headless=true -cp api-web-0.1.jar;../ mx.AdminWebApiBootstrap >nul 2>nul   

## linux 开发测试环境：   
nohup java -Djava.net.preferIPv4Stack=true -Djava.awt.headless=true -cp api-web-0.1.jar:../ mx.AdminWebApiBootstrap >/dev/null 2>&1 &   

## linux 生产环境：
nohup java -server -Xms1024m -Xmx4096m -XX:PermSize=256M -XX:MaxPermSize=512m -Djava.net.preferIPv4Stack=true -Djava.awt.headless=true -cp api-web-0.1.jar:../ mx.AdminWebApiBootstrap >/dev/null 2>&1 &   
