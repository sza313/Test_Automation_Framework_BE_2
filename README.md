## Test Automation Framework BE 2


### How to add new tests:

##### 1. step: Add java file
Add a new ``*Test.java`` file using the already ones (e.g. ``DummyTest.java``). The file name has to contain Test, as Surefire Plugin can only run tests, whose file name includes 'Test'.

```java
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DummyTest {

    @Autowired
    private JsonReader jsonReader;
    @Autowired
    private DataBaseChecker dataBaseChecker;

    @Test
    @EnabledIf("${tests.dummy.enabled:false}")
    public void dummyTest() {
        // GIVEN
        TestCases testCases = jsonReader.readJson("src/test/resources/dummyTest.json");

        // WHEN/THEN
        dataBaseChecker.runTestCase(testCases);
    }
}
```

##### 2. step: Add json file, and specify the filename it in java file
Add a ``*.json`` to the ``src/test/resources`` folder, and add the file name to the already created ``*Test.java`` file into the line:

```java
        TestCases testCases = jsonReader.readJson("src/test/resources/{jsonFileName}.json");
```

The structure of the json must be the following:

```json
{
    "testName": "Dummy Test",
    "testData": [
        {
            "sqlQuery": "SELECT column_name_1, column_name_2 FROM table1 WHERE column_name_1 = 'some value'",
            "expectedValues": {
                "COLUMN_NAME_1": "some value",
                "COLUMN_NAME_2": "other value"
            }
        },
        ...
        {
            "sqlQuery": "SELECT column_name_3, column_name_4 FROM table2 WHERE column_name_3 = 'third value'",
            "expectedValues": {
                "COLUMN_NAME_3": "third value",
                "COLUMN_NAME_4": "any value"
            }
        }
    ]
}
```

##### Keep in mind:
The expectedValues must contain key-value pairs, where key is the expected column name (UPPER_CASE) and the value is the expected value belonging to that column. Always filter with your SQL queries until you receive only one line of result, as the framework is only capable of handling that.

### Database driver:
For the Database connection to work, a database driver is needed. In this dummy test the Oracle database driver is used as follows:

The ``pom.xml`` contains the driver's jar as a dependency. The jar is also included directly in the project folder, but could be also referenced if the company has a separate repository where this jar is stored.
In case there is no repository available:

```xml
    <!-- Database Driver -->
    <dependency>
      <groupId>com.oracle</groupId>
      <artifactId>ojdbc8</artifactId>
      <version>18.3.0</version>
      <scope>system</scope>
      <systemPath>${project.basedir}/driver/ojdbc8.jar</systemPath>
    </dependency>
```

In case there is a repository available:

```xml
    <!-- Database Driver -->
    <dependency>
      <groupId>com.oracle</groupId>
      <artifactId>ojdbc8</artifactId>
      <version>18.3.0</version>
    </dependency>
```

Spring also needs to be set up, so it may be able to connect to the database. For this it needs to have some properties set in the ``src/main/resources/application.properties``.
The following example is for an oracle database in localhost.

```
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.password=password
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
spring.datasource.username=username
```