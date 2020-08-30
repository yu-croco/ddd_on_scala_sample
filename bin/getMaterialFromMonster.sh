#!/usr/bin/env bash
curl \
-X POST \
-H 'Content-Type: application/json' \
-d '{"hunterId":1, "monsterId": 1}' \
"http://localhost:9001/hunters/get_material_from_monster" | jq