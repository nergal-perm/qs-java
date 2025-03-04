**Step-by-Step Implementation Guide for "Testing Without Mocks" with Nullables**
*For AI Agents Assisting with Java/Spring Boot Codebases*

---

### **1. Architectural Setup**

#### **1.1 Adopt A-Frame Architecture**

- Structure code into three layers:
    - **Application/UI**: Entry points (controllers, CLI handlers)
    - **Logic**: Pure computation (domain models, business rules)
    - **Infrastructure**: External I/O (databases, HTTP clients)

```java
// Example Spring Boot service structure
@Service
public class FoodTrackerService { // Application Layer
  private final NutritionLogic nutritionLogic; // Logic Layer
  private final Neo4JWrapper neo4j; // Infrastructure Wrapper

  public FoodTrackerService(NutritionLogic nutritionLogic, Neo4JWrapper neo4j) {
    this.nutritionLogic = nutritionLogic;
    this.neo4j = neo4j;
  }
}
```

---

### **2. Infrastructure Implementation**

#### **2.1 Create Infrastructure Wrappers**

- Wrap external systems with dedicated classes:

```java
public class Neo4JWrapper { 
  private final Driver driver;
  
  public Neo4JWrapper(Driver driver) {
    this.driver = driver;
  }
  
  // Production factory
  public static Neo4JWrapper create() {
    return new Neo4JWrapper(GraphDatabase.driver("neo4j://prod-url"));
  }
  
  // Nullable factory
  public static Neo4JWrapper createNull() {
    return new Neo4JWrapper(new NullDriver());
  }
}
```

---

### **3. Nullable Implementation**

#### **3.1 Add CreateNull Factory**

- Implement configurable null instances:

```java
public class Neo4JWrapper {
  // ...
  
  public static Neo4JWrapper createNull(List<FoodItem> stubData) {
    return new Neo4JWrapper(new NullDriver(stubData));
  }
  
  private static class NullDriver extends Driver {
    private final List<FoodItem> stubData;
    
    public NullDriver(List<FoodItem> stubData) {
      this.stubData = stubData;
    }
    
    @Override
    public Session session() {
      return new NullSession(stubData);
    }
  }
}
```

---

### **4. Testing Strategy**

#### **4.1 State-Based Tests**

- Verify outcomes rather than interactions:

```java
@Test
public void calculates_nutrition_totals() {
  // Arrange
  var stubData = List.of(new FoodItem("Apple", 95));
  var neo4j = Neo4JWrapper.createNull(stubData);
  var service = new FoodTrackerService(new NutritionLogic(), neo4j);
  
  // Act
  DailyReport report = service.generateDailyReport();
  
  // Assert
  assertThat(report.totalCalories()).isEqualTo(95); // State verification
}
```


#### **4.2 Output Tracking**

- Monitor infrastructure outputs:

```java
public class Neo4JWrapper {
  private final OutputTracker<Query> queryTracker;
  
  public Neo4JWrapper(Driver driver) {
    this.queryTracker = new OutputTracker<>();
  }
  
  public List<Query> executedQueries() {
    return queryTracker.data();
  }
  
  public void executeQuery(Query query) {
    // ... real execution ...
    queryTracker.track(query); // Track in both prod and nulled instances
  }
}
```

---

### **5. Legacy Code Conversion**

#### **5.1 Replace Mocks Incrementally**

- Convert existing Mockito tests:

```java
// BEFORE (Mock-based)
@Mock Neo4JDriver driver;
@Test
public void testWithMocks() {
  when(driver.execute(any())).thenReturn(stubData);
  // ... test logic ...
}

// AFTER (Nullable-based)
@Test
public void testWithNullables() {
  var neo4j = Neo4JWrapper.createNull(stubData);
  var service = new FoodTrackerService(neo4j);
  // ... test logic ...
  assertThat(neo4j.executedQueries()).contains(expectedQuery);
}
```

---

### **6. Error Handling**

#### **6.1 Implement Paranoic Telemetry**

- Validate failure scenarios:

```java
@Test
public void handles_database_failures() {
  var neo4j = Neo4JWrapper.createNull()
    .withSimulatedError(new DatabaseException("Connection failed"));
  
  assertThatThrownBy(() -> service.saveNutritionData(data))
    .isInstanceOf(NutritionPersistenceException.class);
}
```

---

### **7. Code Generation Patterns**

#### **7.1 Controller Implementation**

- Generate Spring Boot controllers with nullable dependencies:

```java
@RestController
public class FoodEntryController {
  
  private final FoodTrackerService service;
  
  // Generated constructor with nullable injection
  public FoodEntryController(
    @Autowired(required = false) FoodTrackerService service
  ) {
    this.service = service != null ? service : FoodTrackerService.createNull();
  }
}
```

---

### **Key Requirements for Copilot**

1. **Always** generate createNull() factories for infrastructure classes
2. **Prefer** state assertions over interaction verification
3. **Implement** output tracking for write operations
4. **Structure** tests using sociable testing patterns
5. **Convert** existing mocks to nullables during refactoring
6. **Separate** logic (nutrition calculations) from infrastructure (Neo4J access)