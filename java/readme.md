# Maven notes

## create maven initization

```shell
# please replace module to the specified module name.
mvn archetype:generate -DgroupId=net.zhuxiaodong.algorithm.practice.module \
                       -DartifactId=module \
                       -Dversion=1.0.0-SHAPSHOT \
                       -Dpackage=net.zhuxiaodong.algorithm.practice.module \
                       -DinteractiveMode=false
```

## edit pom.xml

copy java\array\pom.xml content to new generate pom.xml file.

## generic mvn operation

```shell
mvn compile
mvn package
```

## unit test

Depend on jUnit 5.0 version and jacoco maven plugin.

```shell
mvn test
```

After run unit test, please see `target\site\jacoco\index.html` to view unit test coverage.
