# Log4j 2 Configuration Using Properties File

## 1. Loading the Properties File

Create a file named `log4j2.properties` inside `src/main/resources`.

```text
src/main/resources/log4j2.properties
```

- Log4j 2 automatically loads `log4j2.properties` when it is in the correct classpath location.
- `PropertyConfigurator.configure()` is used in Log4j 1.x, not in the standard Log4j 2 configuration approach.
- Log4j 2 uses different appender and layout configuration syntax.

## Case 1: HtmlLayout + File Appender

**Purpose:** Store log messages in an HTML file.

```properties
status=WARN
name=HTMLFileConfiguration
appender.file.type=File
appender.file.name=HTMLFile
appender.file.fileName=D:/log.html
appender.file.append=true
appender.file.layout.type=HtmlLayout
rootLogger.level=info
rootLogger.appenderRef.file.ref=HTMLFile
```

- `INFO`: Logs INFO and higher-severity messages.
- `File`: Writes logs to a file.
- `fileName`: Specifies the output file path.
- `append = true`: Appends logs to the existing file.
- `HtmlLayout`: Formats log events as HTML.

## Case 2: Multiple Appenders

**Purpose:** Display logs on the console and save them to a file simultaneously.

```properties
status=WARN
name=MultipleAppenderConfiguration
appender.console.type=Console
appender.console.name=ConsoleAppender
appender.console.target=SYSTEM_OUT
appender.console.layout.type=PatternLayout
appender.console.layout.pattern=%m%n
appender.file.type=File
appender.file.name=FileAppender
appender.file.fileName=D:/log.txt
appender.file.append=true
appender.file.layout.type=PatternLayout
appender.file.layout.pattern=%d{yyyy-MM-dd HH:mm:ss} %p %c %m%n
rootLogger.level=debug
rootLogger.appenderRef.console.ref=ConsoleAppender
rootLogger.appenderRef.file.ref=FileAppender
```

- `DEBUG`: Enables DEBUG and higher-severity messages.
- `Console`: Prints logs to the console.
- `File`: Writes logs to a file.
- `PatternLayout`: Defines the format of each log message.
- Multiple appenders send the same log events to different destinations.

## Case 3: XmlLayout + File Appender

**Purpose:** Store log events in XML format.

```properties
status=WARN
name=XMLFileConfiguration
appender.file.type=File
appender.file.name=XMLFile
appender.file.fileName=D:/info.xml
appender.file.append=true
appender.file.layout.type=XmlLayout
rootLogger.level=info
rootLogger.appenderRef.file.ref=XMLFile
```

- `File`: Writes logs to a file.
- `XmlLayout`: Formats log events as XML.
- `append = true`: Appends logs to the existing file.

## Case 4A: RollingFile + PatternLayout

**Purpose:** Automatically roll over the log file when it reaches a specified size.

```properties
status=WARN
name=RollingFileConfiguration
appender.rolling.type=RollingFile
appender.rolling.name=RollingFileAppender
appender.rolling.fileName=log_info.txt
appender.rolling.filePattern=log_info-%i.txt
appender.rolling.append=true
appender.rolling.layout.type=PatternLayout
appender.rolling.layout.pattern=%d{yyyy-MM-dd HH:mm:ss} %p %c %M %t %r %L %m%n
appender.rolling.policies.type=Policies
appender.rolling.policies.size.type=SizeBasedTriggeringPolicy
appender.rolling.policies.size.size=10 KB
appender.rolling.strategy.type=DefaultRolloverStrategy
appender.rolling.strategy.max=3
rootLogger.level=debug
rootLogger.appenderRef.rolling.ref=RollingFileAppender
```

- `RollingFile`: Writes logs and supports rollover.
- `filePattern`: Specifies the names of archived log files.
- `SizeBasedTriggeringPolicy`: Triggers rollover based on file size.
- `size = 10 KB`: Sets the rollover size threshold.
- `DefaultRolloverStrategy`: Controls backup-file management.
- `max = 3`: Retains up to three indexed backup files.

## Case 4B: Time-Based RollingFile + PatternLayout

**Purpose:** Roll over the log file daily.

```properties
status=WARN
name=TimeBasedRollingConfiguration
appender.rolling.type=RollingFile
appender.rolling.name=DailyRollingFile
appender.rolling.fileName=log_info1.txt
appender.rolling.filePattern=log_info1-%d{yyyy-MM-dd}.txt
appender.rolling.append=true
appender.rolling.layout.type=PatternLayout
appender.rolling.layout.pattern=%d{yyyy-MM-dd HH:mm:ss} %p %c %M %t %r %L %m%n
appender.rolling.policies.type=Policies
appender.rolling.policies.time.type=TimeBasedTriggeringPolicy
appender.rolling.policies.time.interval=1
appender.rolling.policies.time.modulate=true
rootLogger.level=debug
rootLogger.appenderRef.rolling.ref=DailyRollingFile
```

- `TimeBasedTriggeringPolicy`: Triggers rollover based on time.
- `interval = 1`: Uses one unit of the date pattern's time interval.
- `modulate = true`: Aligns rollover with natural time boundaries.
- `filePattern`: Specifies the names of archived files.
- `%d{yyyy-MM-dd}`: Uses the date in the archived filename.
- Log4j 2 replaces Log4j 1.x's `DailyRollingFileAppender` with `RollingFile` and a time-based triggering policy.

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

| Component                   | Purpose                              |
|-----------------------------|--------------------------------------|
| `File`                      | Writes logs to a file                |
| `Console`                   | Prints logs to the console           |
| `HtmlLayout`                | Formats logs as HTML                 |
| `XmlLayout`                 | Formats log events as XML            |
| `PatternLayout`             | Provides a customizable log format   |
| `RollingFile`               | Supports log-file rollover           |
| `SizeBasedTriggeringPolicy` | Triggers rollover based on file size |
| `TimeBasedTriggeringPolicy` | Triggers rollover based on time      |
| `Multiple appenders`        | Sends logs to multiple destinations  |

**Important:** These examples use Log4j 2 properties-file syntax. The appender types and configuration keys differ from
Log4j 1.x.

**Dependency reminder:** Log4j 2 requires the `log4j-api` and `log4j-core` dependencies for this configuration approach.
If your project uses SLF4J or another logging facade, additional integration dependencies may be required.