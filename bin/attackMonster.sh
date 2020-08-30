#!/usr/bin/env bash
curl \
-X PUT \
-H 'Content-Type: application/json' \
-d '{"monsterId": 1}' \
"http://localhost:9001/hunters/1/attack" | jq