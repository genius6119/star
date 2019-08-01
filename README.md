#  **star** 

#### 介绍
使用SpringCloud的服务器端代码 

#### 软件架构
软件架构说明


#### 安装教程

1. 先启动eureka,再启动config

2. 其他service启动顺序无所谓

3. 互为生产者/消费者（调用其他服务时整合使用feign，参考feign模块）

4. 读取配置文件（例子）：
    spring:
      application:
        name: user-service
      cloud:
        config:
          profile: test
就可以读取https://gitee.com/taigut/star-config下的user-service-test.yml

5. 新建模块时都要注册到eureka中，要在主函数上加@EnableDiscoveryClient

6. 新建的模块通过yml中指定配置中心：
    spring:
     cloud:
        config:
          discovery:
            enabled: true
            service-id: config-server

7.eureka和config中的代码几乎不用变动，其他所有模块端口都对外网不可访问，只有zuul网关的端口对外暴露，通过zuul网关接收请求，调用相应模块，提供服务。

8.所有依赖对应版本号在在外层的pom中；新建模块时要声明为最外层的子项目。





#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)