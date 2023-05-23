run:
	./gradlew run

run-dist: build
	./build/install/app/bin/app

build: clear
	./gradlew installDist
	
clear:
	./gradlew clean

test:
	./gradlew test

test-json: clear build
	./build/install/app/bin/app -f json src/test/resources/fixtures/file_1.json src/test/resources/fixtures/file_2.json
test-yaml: clear build
	./build/install/app/bin/app -f stylish src/test/resources/fixtures/file_1.yml src/test/resources/fixtures/file_2.yml

check:
	./gradlew check

tasks:
	./gradlew tasks

setup:
	./gradlew wrapper --gradle-version 7.4.2

.PHONY: build