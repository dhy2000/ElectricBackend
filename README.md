# Electric Backend

数据库大作业项目 Electric 后端程序。

## 环境说明

本项目采用 Java 语言，Spring Boot 框架，maven 构建工具。

环境要求：
- Java Development Kit 8
- Maven 3.8.2
- Spring Boot 2.5.4 及其他依赖包 (通过 maven 自动下载并导入)

安装好上述依赖环境后，clone 本仓库并执行 maven 的相应命令即可编译并构建工程。

## 构建及运行说明

工程源代码中未填入数据库地址以及用户名密码，需在运行时将它们作为参数传入。（对于在线部署版本，数据库地址、用户名和密码存储在 GitLab 仓库的 CI Variables 中并在 CI/CD 自动部署过程传入）

本工程已经集成了 Swagger 工具用来自动生成在线的 API 文档。在线 API 文档的相对地址：`/swagger-ui/index.html` （完整地址示例：`http://localhost:8000/swagger-ui/index.html`）

### 本地编译和运行

构建工程命令示例：
```shell
cd ElectricBackend  # 进入工程目录
mvn compile && mvn package # 使用 maven 编译并打包为 jar
```

执行完毕后在 `target` 子目录下生成可执行的 jar 包，使用命令运行该 jar 包。假设 jar 包名为 `SpringApplication.jar`，则参考运行命令如下：

```shell
java -jar SpringApplication.jar \
  --spring.datasource.url=jdbc:mysql://[数据库地址:端口]/db_course_2021?useUnicode=true&characterEncoding=utf8 \
  --spring.datasource.username=[数据库用户名] \
  --spring.datasource.password=[数据库密码] \
  --file.file-dir=[文件存储目录]
```

其中 `文件存储目录` 用于存储用户头像等文件内容。

本地运行的默认端口为 `8000` （端口配置位于 `src/main/resources/application.yml` 中）

### 使用 docker 运行

工程目录内已经提供 `Dockerfile`，可使用 `docker build -t` 命令构建 Docker 镜像。

从镜像启动 docker 容器时，需向容器以环境变量形式传入数据库的地址、用户名与密码，同时若需要将文件存储在本地，需将主机目录映射至 docker 容器内的 `/storage` 目录。

构建和启动 docker 容器命令示例：

```shell
docker build -t "electric-backend:latest" . # 构建 docker 镜像, 镜像名为 electric-backend, 标签为 latest
docker run --restart=unless-stopped --name=electric-backend -d \\
  -p 8001:8000 \\ # IP 地址映射，该后端服务实际监听主机的 8001 端口
  -e DB_ADDRESS_URL="jdbc:mysql://[数据库地址:端口]/db_course_2021?useUnicode=true&characterEncoding=utf8" \\
  -e DB_USER="[数据库用户名]" \\
  -e DB_PASSWORD="[数据库密码]" \\
  -v [主机文件存储目录]:/storage \\
"electric-backend:latest" 
```

## 在线部署说明

本工程采用 GitLab CI/CD 进行在线部署，相应自动部署脚本位于 `.gitlab-ci.yml` 中。

数据库配置信息存储在 CI Variables 中，共包括：
- `DB_ADDRESS_URL`: JDBC 连接数据库的完整 URL
- `DB_USER`: 数据库用户名
- `DB_PASSWORD`: 数据库密码
- `PROJECT_NAME`: 工程名称, 用于构建 docker 镜像以及容器
