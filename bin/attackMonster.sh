#!/usr/bin/env bash
curl \
-X POST \
-H 'Content-Type: application/json' \
-d '{"hunterId":1, "monsterId": 2}' \
"http://localhost:9001/hunters/attack"