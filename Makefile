path = $(shell pwd)

run: install-dist comparison-example

run-dist:
	./build/install/app/bin/app

install-dist:
	./gradlew clean install

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

test:
	./gradlew test

comparison-example:
	./build/install/app/bin/app src/test/resources/file1.json src/test/resources/file2.json
	@echo "--------------------------------------------------------------"
	./build/install/app/bin/app src/test/resources/fileRec1.json src/test/resources/fileRec2.json

report: test
	./gradlew jacocoTestReport
	@echo "\n\nOpen the following file in any browser:"
	@echo "\033[34mfile://${path}/build/jacocoHtml/index.html\033[0m"
	@echo "---------------------------------------------------------------------------------------------------"
	@w3m -dump file://${path}/build/jacocoHtml/index.html
	@echo "---------------------------------------------------------------------------------------------------"

build:
	./gradlew clean build
