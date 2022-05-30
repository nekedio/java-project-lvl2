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
	./build/install/app/bin/app example/file1.json example/file2.json

report:
	./gradlew jacocoTestReport
#	@echo "\n\nOpen the following file in any browser:"
#	@echo "\033[34mfile:///home/nekedio/he/app/build/jacocoHtml/index.html\033[0m"
#	@echo "---------------------------------------------------------------------------------------------------"
#	@w3m -dump file:///home/nekedio/he/app/build/jacocoHtml/index.html
#	@echo "---------------------------------------------------------------------------------------------------"
