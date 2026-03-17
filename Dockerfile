FROM tomcat:10.0-jdk17
COPY dist/SellingMerch.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
