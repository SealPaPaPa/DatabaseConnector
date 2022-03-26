# DatabaseConnector

用 Java 開發資料庫操作的工具。

java -jar DatabaseConnector.jar [driver] [url] [user] [password] [sql]

* Driver
  * com.microsoft.sqlserver.jdbc.SQLServerDriver
  * oracle.jdbc.driver.OracleDriver
  * com.mysql.jdbc.Driver
* Url
  * jdbc:mysql://127.0.0.1:3306/test
  * jdbc:oracle:thin:@127.0.0.1:1521:orcl
  * jdbc:sqlserver://127.0.0.1:1433;DatabaseName=test;maxStatements=0;SelectMethod=cursor
* SQL
  * SELECT * FROM DBA_TABLES
  * SHOW TABLES

