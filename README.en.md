[![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/a-simeshin/java-cbr-uuid-generator/blob/main/README.en.md)
[![ru](https://img.shields.io/badge/lang-ru-blue.svg)](https://github.com/a-simeshin/java-cbr-uuid-generator/blob/main/README.md)

# java-cbr-uuid-generator

Simple implementation of an algorithm for generating unique identifiers for credit histories and other artifacts of the 
banking process in accordance with the requirements [указанием Банка России от 09.09.2019 № 5251-У «О правилах 
присвоения уникального идентификатора договора (сделки), по обязательствам из которого (из которой)формируется 
кредитная история», зарегистрированного Министерством юстиции 
Российской Федерации 30.10.2019 № 56361](https://www.cbr.ru/Queries/UniDbQuery/File/90134/931)

## Usage

Let's get single uid

```java
public class Foo {
    
    public void bar() {
        final String cbrUuid = CBRUUID.getCbrUUID();
    }
}
```

Let's get more than one uid

```java
public class Foo {
    
    public void bar() {
        final List<String> cbrUuids = CBRUUID.getCbrUUID(10);
    }
}
```

## How to avoid collisions when ticks coincide on different hosts

To avoid collisions, I'll suggest setting MAC address somewhere in Configuration section of application.

```java
public class Foo {
    
    public void bar() {
        CBRUUID.ETHERNET_MAC_ADDRESS = new EthernetAddress("00:C0:F0:3D:5B:7C");
        final String cbrUuid = CBRUUID.getCbrUUID();
    }
}
```

## Useful links

* Requirements - https://www.cbr.ru/Queries/UniDbQuery/File/90134/931
* Requirements for usage in Credit History - https://www.cbr.ru/ckki/assignment_unique_id/
* Base library to generate FILETIME notation uuids - https://github.com/cowtowncoder/java-uuid-generator
* Another impl on Kotlin - https://github.com/DmitriiTrifonov/cbrf-uuid-service
* Another impl on Python - https://github.com/Okeydj/cbr-uuid-python
* Another impl on C - https://www.cbr.ru/StaticHtml/File/87854/C.zip
* Usage in difficult situations of banking process - https://www.cbr.ru/ckki/explain/