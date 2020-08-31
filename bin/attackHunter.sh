#!/usr/bin/env bash
curl \
-X PUT \
-H 'Content-Type: application/json' \
-d '{"hunterId":"32de8cb2-76ac-4fc1-a3b4-372e446bf5bf"}' \
"http://localhost:9001/monsters/32de8cb2-76ac-4fc1-a3b4-372e446bf5bf/attack" | jq