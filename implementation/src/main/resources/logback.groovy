appender('STDOUT', ConsoleAppender) {
   encoder(PatternLayoutEncoder) {
      pattern = '%d{HH:mm:ss.SSS} %-5level %logger{36} - %msg%n';
   }
}

logger('com.teampc', DEBUG)
logger('org.hibernate', WARN)
root(DEBUG, ['STDOUT'])
