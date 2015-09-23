#!/bin/bash

PORT=9000
java -Ddw.server.applicationConnectors[0].port=$PORT -Ddw.server.adminConnectors[0].port=$((PORT + 1)) -jar ./build/libs/server-all.jar server testTool.yml
