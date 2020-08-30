# DDD on Scala
Scala（PlayFramework）を使い、なんちゃってモンハンの世界をDomain-Driven Designで実装した。

## 構成
アプリケーション全体としては以下の構成となっている。
いわゆるオニオンアーキテクチャに近い形である。
![architecture](./public/images/architecture.png)

読み取りアクセス（GET）と書き込みアクセス（POST/PUT/DELETE）では処理フローを以下のように分けている（アーキテクチャレベルでの話ではないのでCQRSというよりはCQSかな？）。
![access flow](./public/images/access_flow.png)

## ドメインモデル図

![domain model](./public/images/domain_model.png)

## ユースケース図
### ハンター主体のケース
- ハンターがモンスターを攻撃する
    - 確認コマンド: `bin/attackMonster.sh`

![hunter_attacks_monster](./public/images/hunter_attacks_monster.png)

- ハンターが倒したモンスターから素材を剥ぎ取る
    - 確認コマンド: `bin/getMaterialFromMonster.sh`

![get_material_from_monster](./public/images/get_material_from_monster.png)

### モンスター主体のケース
- モンスターがハンターを攻撃する
    - 確認コマンド: `bin/attackHunter.sh`

![monster_attacks_hunter](./public/images/monster_attacks_hunter.png)


## 技術スタック
- Scala v2.12.8
- PlayFramework v2.8
- cats
- Eff
- Domain-Driven Design
- CQS
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

## セットアップ

```bash
$ git clone
# APIの動作確認に使っているShellScriptではjqを使っているので、動作確認したい場合には入れる
$ brew install jq

# Dockerで全部動かしたい場合
$ docker-compose up

# PlayFrameworkはローカルで動かしたい場合
$ bin/run.sh

# Seed実行でサンプルデータを投入できる
$ bin/seed.sh
```

## その他
### DBマイグレーション
- [evolutions](https://www.playframework.com/documentation/2.8.x/Evolutions) と [slick-codegen](https://scala-slick.org/doc/3.2.0/code-generation.html) を使っている
- `conf/evolutions/default` 配下にSQLファイルを置いてサーバーを起動することで、DBマイグレーションが実行される
- マイグレーション結果をコードに反映させるには `sbt codegen/run` を行う

## 参考
- [ddd-on-scala(github)](https://github.com/crossroad0201/ddd-on-scala)
- [ddd-on-scala(Speaker Deck)](https://speakerdeck.com/crossroad0201/scala-on-ddd)
- [実践DDD本 第7章「ドメインサービス」～複数の物を扱うビジネスルール～](https://codezine.jp/article/detail/10318)
- [混乱しがちなサービスという概念について](https://blog.j5ik2o.me/entry/2016/03/07/034646)
- [scalazやcatsを使ってFunctional DDDを試してみる](https://qiita.com/uryyyyyyy/items/4a846be75f1554727f71)
- [ScalaのEffを使ってDDDのUseCase層をいい感じに書いてみる](https://qiita.com/yu-croco/items/859328beda388f4f4393)