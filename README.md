
# arangodb-wal-client

ArangoDB Write-Ahead-Log Client in Java

[ ![TravicCI](https://travis-ci.org/stackmagic/arangodb-wal-client.svg?branch=master) ](https://travis-ci.org/stackmagic/arangodb-wal-client)
[ ![Download](https://api.bintray.com/packages/stackmagic/maven/arangodb-wal-client/images/download.svg) ](https://bintray.com/stackmagic/maven/arangodb-wal-client/_latestVersion)

# downloading

## gradle

the [jcenter() shortcut requires at least gradle 1.7](http://www.gradle.org/docs/1.7/release-notes#jcenter-repository-support)

```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'net.swisstech:arangodb-wal-client:+'
}
```

## maven

```xml
<repository>
    <id>jcenter</id>
    <url>https://jcenter.bintray.com/</url>
</repository>
```

```xml
<dependency>
    <groupId>net.swisstech</groupId>
    <artifactId>arangodb-wal-client</artifactId>
    <version>...</version>
</dependency>
```

# Compatibility

## 2.0.0

* OK arangodb-3.3.x
* OK latest 3.4-RC as of 2018-10-13 (RC2 won't start in docker or takeks too long)

# Changelog

## 2.0.0

* upgrade gradle
* run inttests with docker
* fix tests/pojos

# TODO

* WalEvent: prevent parsing of the `data` field and just use it as-is (saves a bit of cpu/mem)

