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
	./build/install/app/bin/app -f stylish src/test/resources/file_1.json src/test/resources/file_2.json
test-yaml: clear build
	./build/install/app/bin/app -f plain src/test/resources/file_1.yml src/test/resources/file_2.yml

check:
	./gradlew check

tasks:
	./gradlew tasks

.PHONY: build