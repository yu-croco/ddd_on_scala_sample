#!/usr/bin/env bash
curl \
-X POST \
-H 'Content-Type: application/json' \
-d '{"userId":1, "taskName":"prepare sleep", "taskDetail": "prepare sleep asap"}' \
"http://localhost:9001/tasks"