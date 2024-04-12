# 사용할 베이스 이미지 지정
FROM openjdk:17-jre-slim

# 포트 8080 열기
EXPOSE 8080

# 애플리케이션 파일을 컨테이너로 복사
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]