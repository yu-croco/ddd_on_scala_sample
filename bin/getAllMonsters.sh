#!/usr/bin/env bash
curl \
-H 'Content-Type: application/json' \
"http://localhost:9011/monsters" | jq