#!/usr/bin/env bash
curl \
-X PUT \
-H 'Content-Type: application/json' \
-d '{"monsterId": 1}' \
"http://localhost:9001/hunters/02de8cb2-76ac-4fc1-a3b4-372e446bf0bf/attack" | jq