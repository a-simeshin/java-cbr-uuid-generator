[![en](https://img.shields.io/badge/lang-en-blue.svg)](https://github.com/a-simeshin/java-cbr-uuid-generator/blob/main/README.en.md)
[![ru](https://img.shields.io/badge/lang-ru-blue.svg)](https://github.com/a-simeshin/java-cbr-uuid-generator/blob/main/README.md)

# java-cbr-uuid-generator

Реализация генератора UIDов на Java по требованиям ЦБ РФ в соответствии
с [указанием Банка России от 09.09.2019 № 5251-У «О правилах присвоения уникального идентификатора договора (сделки), по обязательствам из которого (из которой)
формируется кредитная история», зарегистрированного Министерством юстиции Российской Федерации 30.10.2019 № 56361](https://www.cbr.ru/Queries/UniDbQuery/File/90134/931)

## Использование

Получение одного uuid

```java
public class Foo {

    public void bar() {
        final String cbrUuid = CBRUUID.getCbrUUID();
    }
}
```

Получение нескольких uuid'ов

```java
public class Foo {

    public void bar() {
        final List<String> cbrUuids = CBRUUID.getCbrUUID(10);
    }
}
```

## Избежание коллизий при совпадении тиков на разных хостах

Для избежания коллизий предлагается использовать MAC адрес

```java
public class Foo {

    public void bar() {
        CBRUUID.ETHERNET_MAC_ADDRESS = new EthernetAddress("00:C0:F0:3D:5B:7C");
        final String cbrUuid = CBRUUID.getCbrUUID();
    }
}
```

## Полезные ссылки

* Требования - https://www.cbr.ru/Queries/UniDbQuery/File/90134/931
* Требования по использованию в кредитной истории - https://www.cbr.ru/ckki/assignment_unique_id/
* За основу взят генератор UID в FILETIME нотации - https://github.com/cowtowncoder/java-uuid-generator
* Аналог на котлине в виде микросервиса - https://github.com/DmitriiTrifonov/cbrf-uuid-service
* Аналог на питоне - https://github.com/Okeydj/cbr-uuid-python
* Аналог на СИ - https://www.cbr.ru/StaticHtml/File/87854/C.zip
* Когда применяется данный алгоритм и в каких случаях - https://www.cbr.ru/ckki/explain/