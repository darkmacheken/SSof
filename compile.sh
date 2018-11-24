mvn -f static-analyser/pom.xml clean package -DskipTests
cp static-analyser/target/static-analyser.jar .