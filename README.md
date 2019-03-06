## Test Automation Framework BE 2


### How to add new tests:

##### 1. step: Add java file
Add a new \*Test.java file using the already ones (e.g. DummyTest.java). The file name has to contain Test, as Surefire Plugin can only run tests, whose file name includes 'Test'.

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
Add a \*.json to the src/test/resources folder, and add the file name to the already created \*Test.java file into the line:

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