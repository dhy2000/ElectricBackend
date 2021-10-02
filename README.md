# Electric Backend

数据库大作业项目 Electric 后端程序。

## 环境说明

本项目采用 Java 语言，SpringBoot 框架，maven 构建工具。

环境要求：
- Java Development Kit 8
- Maven 3.8.2
- SpringBoot 2.5.4 及其他依赖包 (通过 maven 自动下载并导入)

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
