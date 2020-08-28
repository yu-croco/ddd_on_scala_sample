#!/usr/bin/env bash
curl \
-X PUT \
-H 'Content-Type: application/json' \
-d '{"hunterId":1, "monsterId": 2}' \
"http://localhost:9001/monsters/attack"