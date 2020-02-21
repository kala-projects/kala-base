# kala common
Project on Github: [kala-projects/kala-common](https://github.com/kala-projects/kala-common)
Project on Gitee: [Glavo/kala-common](https://gitee.com/glavo-gitee/kala-common)


## Adding Kala common to your build

First, add the jcenter repository to your project configuration file:

Maven:
```xml
<project>
...
  <repositories>
    <repository>
      <id>jcenter</id>
      <url>https://jcenter.bintray.com</url>
    </repository>
  </repositories>
...
</project>
```

Gradle:
```groovy
repositories {
    jcenter()
}
```

Then add dependencies:

Maven:
```xml
<dependency>
  <groupId>asia.kala</groupId>
  <artifactId>kala-common</artifactId>
  <version>0.2.0</version>
  <type>pom</type>
</dependency>
```

Gradle:
```groovy
implementation group: 'asia.kala', name: 'kala-common', version: '0.2.0'
```

