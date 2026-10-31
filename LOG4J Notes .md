# Log4j Configuration Using Properties File

## 1. Loading the Properties File

```java
static {
    PropertyConfigurator.configure("Source of .properties file");
}
```

- `static` block executes when the class is initialized.
- `PropertyConfigurator.configure()` loads the Log4j configuration from a properties file.
- This syntax is used in **Log4j 1.x**.

## Case 1: HTMLLayout + FileAppender

**Purpose:** Store log messages in an HTML file.

```properties
# Set logger level and appender
log4j.rootLogger=INFO,R
# Configure FileAppender
log4j.appender.R=org.apache.log4j.FileAppender
log4j.appender.R.File=D:/log.html
log4j.appender.R.append=true
# Configure HTMLLayout
log4j.appender.R.layout=org.apache.log4j.HTMLLayout
```

- `INFO`: Logs INFO and higher-severity messages.
- `FileAppender`: Writes logs to a file.
- `append=true`: Appends logs to the existing file.
- `HTMLLayout`: Formats logs as HTML.

## Case 2: Multiple Appenders

**Purpose:** Display logs on the console and save them to a file simultaneously.

```properties
log4j.rootLogger=DEBUG,R,C
# ConsoleAppender with SimpleLayout
log4j.appender.C=org.apache.log4j.ConsoleAppender
log4j.appender.C.layout=org.apache.log4j.SimpleLayout
# FileAppender with HTMLLayout
log4j.appender.R=org.apache.log4j.FileAppender
log4j.appender.R.File=D:/log.html
log4j.appender.R.append=true
log4j.appender.R.layout=org.apache.log4j.HTMLLayout
```

- `DEBUG`: Enables DEBUG and higher-severity messages.
- `C`: Sends logs to the console.
- `R`: Sends logs to a file.
- `SimpleLayout`: Displays a simple log format.
- `HTMLLayout`: Formats file logs as HTML.

## Case 3: XMLLayout + FileAppender

**Purpose:** Store log messages in XML format.

```properties
log4j.rootLogger=INFO,R
log4j.appender.R=org.apache.log4j.FileAppender
log4j.appender.R.File=D:/info.xml
log4j.appender.R.append=true
log4j.appender.R.layout=org.apache.log4j.xml.XMLLayout
```

- `FileAppender`: Writes logs to a file.
- `XMLLayout`: Formats log events as XML.
- `append=true`: Appends logs to the existing file.

## Case 4A: RollingFileAppender + PatternLayout

**Purpose:** Automatically roll over the log file when it reaches a specified size.

```properties
log4j.rootLogger=DEBUG,R
log4j.appender.R=org.apache.log4j.RollingFileAppender
log4j.appender.R.File=log_info.txt
log4j.appender.R.MaxFileSize=10KB
log4j.appender.R.MaxBackupIndex=3
log4j.appender.R.append=true
log4j.appender.R.layout=org.apache.log4j.PatternLayout
log4j.appender.R.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %p %c %M %t %r %L %m%n
```

- `RollingFileAppender`: Creates backup log files when the size limit is reached.
- `MaxFileSize=10KB`: Sets the maximum file size before rollover.
- `MaxBackupIndex=3`: Retains up to three backup files.
- `PatternLayout`: Defines the format of each log message.

## Case 4B: DailyRollingFileAppender + PatternLayout

**Purpose:** Roll over the log file according to a time pattern.

```properties
log4j.rootLogger=DEBUG,R
log4j.appender.R=org.apache.log4j.DailyRollingFileAppender
log4j.appender.R.File=log_info1.txt
log4j.appender.R.append=true
log4j.appender.R.datePattern='.'yyyy-MM-dd
log4j.appender.R.layout=org.apache.log4j.PatternLayout
log4j.appender.R.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %p %c %M %t %r %L %m%n
```

- `DailyRollingFileAppender`: Rolls over the log file based on time.
- `datePattern`: Specifies the rollover schedule.
- `PatternLayout`: Defines the log message format.

**Note:** The original pattern `'.'yyyy-MM-dd-HH-mm` specifies minute-level date boundaries, not daily rollover.

## Pattern Conversion Symbols

| Symbol | Meaning                              |
|--------|--------------------------------------|
| `%d`   | Date and time                        |
| `%p`   | Logging level                        |
| `%c`   | Logger name                          |
| `%M`   | Method name                          |
| `%t`   | Thread name                          |
| `%r`   | Milliseconds since application start |
| `%L`   | Source line number                   |
| `%m`   | Log message                          |
| `%n`   | New line                             |

## Quick Revision

| Component                  | Purpose                             |
|----------------------------|-------------------------------------|
| `FileAppender`             | Writes logs to a file               |
| `ConsoleAppender`          | Prints logs to the console          |
| `HTMLLayout`               | Formats logs as HTML                |
| `XMLLayout`                | Formats logs as XML                 |
| `SimpleLayout`             | Provides a simple log format        |
| `PatternLayout`            | Provides a customizable log format  |
| `RollingFileAppender`      | Rolls over logs based on file size  |
| `DailyRollingFileAppender` | Rolls over logs based on time       |
| Multiple appenders         | Sends logs to multiple destinations |

**Important:** These examples use Log4j 1.x syntax. Log4j 1.x is end-of-life; your current project uses Log4j2, which
has different configuration syntax.
