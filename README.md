# tw-homework-backend
thoughtworks 作业之后端应用 

## 功能描述

/geo 接口，会获取客户端的ip，并返回ip对应的国家和城市

## 截图

执行历史
 ![执行历史](https://iyomi-public.oss-cn-shenzhen.aliyuncs.com/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202018-04-22%2015.16.28.png)
 
 工作流
 ![工作流](https://iyomi-public.oss-cn-shenzhen.aliyuncs.com/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202018-04-22%2015.16.50.png)
 
 部署效果
 ![效果](https://iyomi-public.oss-cn-shenzhen.aliyuncs.com/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202018-04-22%2015.17.07.png)

## CI&CD

持续集成工作流： 提交代码 -》自动构建-》本地测试-》根据分支选择不同的环境部署
文件说明：

1. Jenkins 工作流描述文件
2. Dockerfile 构建docker镜像描述文件

 ## Build Setup
 
 ```bash
 #build package
 mvn clean package
 
 #run server , server will listen at 9000
java -jar target/backend-1.0-SNAPSHOT.jar

 ```
 
