run: install-dist comparison-example

run-dist:
	./build/install/app/bin/app -h

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

report:
	./gradlew jacocoTestReport
	@echo "\n\nOpen the following file in any browser:"
	@echo "\033[34mfile:///home/nekedio/he/app/build/jacocoHtml/index.html\033[0m"
	@echo "---------------------------------------------------------------------------------------------------"
	@w3m -dump file:///home/nekedio/he/app/build/jacocoHtml/index.html
	@echo "---------------------------------------------------------------------------------------------------"

build:
	./gradlew clean build
