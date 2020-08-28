# DDD on Scala

Scala(PlayFramework)でDomain-Driven Design構成のAPIサンプル

## 概要
なんちゃってモンスターハンターを舞台としたモデリングを展開している。
モンスターとハンターが存在し、それぞれのもつ機能をDDDで実装している。

ユースケース図などはWIP

## 技術スタック
- Scala v2.12.8
- PlayFramework v2.8
- cats
- Eff
- Domain-Driven Design
- CQRS(CQS)
- Docker

## 構成

```
├── Dockerfile
├── DockerfileDb
├── README.md
├── app
│    ├── Module.scala // DI
│    ├── adapter // Adapter layer(e.g. controllers)
│    ├── domain // Domain layer
│    ├── infrastructure // infra layer(e.g. DTO, repositoryImpl)
│    └── usecase // useCase(application) layer
├── bin // 動作確認用のツールなど
├── build.sbt
├── codegen
├── conf
├── docker-compose.yml
...
```

## 起動
- Dockerで全部動かしたい場合
    - `docker-compose up`
- PlayFrameworkはローカルで動かしたい場合
    - `bin/run.sh`

## Seed
- `bin/seed.sh`でサンプルデータを投入できる

## API動作確認
### ハンター主体
- ハンターがモンスターを攻撃する
    - `bin/attackMonster.sh`

### モンスター主体

## DBマイグレーション
- [evolutions](https://www.playframework.com/documentation/2.8.x/Evolutions) と [slick-codegen](https://scala-slick.org/doc/3.2.0/code-generation.html) を使っている
- `conf/evolutions/default` 配下にSQLファイルを置いてサーバーを起動することで、DBマイグレーションが実行される
- マイグレーション結果をコードに反映させるには `sbt codegen/run` を行う

## 参考
- [ddd-on-scala(github)](https://github.com/crossroad0201/ddd-on-scala)
- [ddd-on-scala(Speaker Deck)](https://speakerdeck.com/crossroad0201/scala-on-ddd)
- [実践DDD本 第7章「ドメインサービス」～複数の物を扱うビジネスルール～](https://codezine.jp/article/detail/10318)
- [混乱しがちなサービスという概念について](https://blog.j5ik2o.me/entry/2016/03/07/034646)
- [scalazやcatsを使ってFunctional DDDを試してみる](https://qiita.com/uryyyyyyy/items/4a846be75f1554727f71)
