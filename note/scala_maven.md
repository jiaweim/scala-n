# 使用 Maven 管理 Scala

2025-02-03 ⭐
@author Jiawei Mao
***

## 简介

Maven 是一个项目构建和管理工具，可以大大简化标准项目的构建，Maven 用户通常只需查看其 pom.xml 即可了解另一个 Maven 项目的结构。Maven 是一种基于插件的架构，可以轻松地将新库和模块添加到现有项目。

## Scala Maven 插件

下面将使用 Scala Maven 插件（以前称为 maven-scala-plugin），这是 Scala 项目的主要插件。

该插件包含来自中央仓库的 Scala，因此如果使用 Maven 进行编译，则无需单独安装 Scala。

## 创建项目

创建新 Scala 项目的最简单的方法是使用 "archetype"。archetype 就是通用的项目模板。Scala Maven 插件为 Scala 项目提供了一个 archetype。

可以按如下方式运行 archetype 插件：

```sh
mvn archetype:generate -DarchetypeGroupId=net.alchim31.maven -DarchetypeArtifactId=scala-archetype-simple
```

如果是第一次使用该插件，Maven 会下载许多 jar 文件。Maven 会解析依赖项并根据需要下载。

然后，Maven 会要求你提供 groupId, artifactId 和 package。简而言之：

- groupId: 通常采用域名倒序，如 com.my-organization
- artifactId：项目名称，如 playground-project
- version：版本号，可以是任何内容，不过建议遵循本本控制予以，如 0.0.1
- package：默认为 groupId，不过也可以修改为其它，如 com.my-organization

groupId 和 artifactId 一起作为该项目的全球唯一标识符。

项目的目录结构：

```
pom.xml
src
    main
        scala
        	com/my-package/… *.scala
        java
        	com/my-package/… *.java
    test
        scala
        	com/my-package/… *.scala
        java
        	com/my-package/… *.java
target …
```

## 参考

- https://docs.scala-lang.org/tutorials/scala-with-maven.html
- https://github.com/davidB/scala-maven-plugin