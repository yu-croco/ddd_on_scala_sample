#!/usr/bin/env bash
curl \
-X POST \
-H 'Content-Type: application/json' \
-d '{"monsterId": "32de8cb2-76ac-4fc1-a3b4-372e446bf5bf"}' \
"http://localhost:9001/hunters/02de8cb2-76ac-4fc1-a3b4-372e446bf0bf/get_material_from_monster" | jq