# Electric Backend

数据库大作业项目 Electric 后端程序。

## 分支说明(新增)

在第一次作业 (小作业) 检查前启用 `develop` 分支用来存放与部署第二次作业 (大作业) 即将上线但第一次作业暂不需要的功能。`develop` 分支和 `master` 分支共用数据库资源，区别仅体现在网站服务的功能上。

访问地址：`develop` 分支远程部署的端口为 `8113` （ `master` 分支仍为 `8112` ），其余访问方式相同。

## 环境说明

本项目采用 Java 语言，Spring Boot 框架，maven 构建工具。

环境要求：
- Java Development Kit 8
- Maven 3.8.2
- Spring Boot 2.5.4 及其他依赖包 (通过 maven 自动下载并导入)

安装好上述依赖环境后，clone 本仓库并执行 maven 的相应命令即可编译并构建工程。

## 构建说明

当前的工程中未填入数据库用户名和密码，需在 `src/main/resources/application.yml` 中补充后方可在本地构建工程。（对于在线部署版本，数据库用户密码以运行参数形式在启动时提供，其值存储在 GitLab 仓库的 CI Variables 中并在 CI/CD 自动部署过程传入）

构建工程命令示例：
```shell
cd ElectricBackend  # 进入工程目录
mvn compile && mvn package # 使用 maven 编译并打包为 jar
```

执行完毕后在 `target` 子目录下生成可执行的 jar 包，直接使用 `java -jar ****.jar` 命令运行即可（其中 `****.jar` 为实际生成的 jar 文件名）。

## 使用说明

- 在线(部署)地址及端口： `http://49.235.193.150:8112` （开发者本人持有的个人云服务器）
- 本地运行的默认端口为 `8000` （端口配置位于 `src/main/resources/application.yml` 中）

本工程已经集成了 Swagger 工具用来自动生成在线的 API 文档。在线 API 文档的相对地址：`/swagger-ui/index.html` （完整地址示例：`http://localhost:8000/swagger-ui/index.html`）
