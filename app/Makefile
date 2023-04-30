run:
	./gradlew run

run-dist: build
	./build/install/app/bin/app

build-run-test: clear build
	./build/install/app/bin/app file_1.json file_2.json

build: clear
	./gradlew installDist
	
clear:
	./gradlew clean

test:
	./gradlew test

check:
	./gradlew check

tasks:
	./gradlew tasks

.PHONY: build