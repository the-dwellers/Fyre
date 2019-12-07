#!/usr/bin/env bash

clear

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd "server"

if [ -d "world/datapacks" ]; then
	rm -rf "world/datapacks"
fi

mkdir "world/datapacks"
cp -r "../../datapack/." "world/datapacks"

clear

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
