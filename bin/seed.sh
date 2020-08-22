#!/usr/bin/env bash

curl http://localhost:9001
echo "[INFO] SEED実行開始！"
docker-compose exec db psql -f seeds/seed.sql -U postgres -d scala_on_ddd_dev
echo "[INFO] SEED実行終了！"
