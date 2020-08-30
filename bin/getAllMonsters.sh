#!/usr/bin/env bash
curl \
-H 'Content-Type: application/json' \
"http://localhost:9001/monsters" | jq