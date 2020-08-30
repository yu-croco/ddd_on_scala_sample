#!/usr/bin/env bash
curl \
-X PUT \
-H 'Content-Type: application/json' \
-d '{"hunterId":1}' \
"http://localhost:9001/monsters/1/attack" | jq