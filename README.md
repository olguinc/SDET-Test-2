# 🎲 Dice Distribution Validator Challenge

End-to-end test automation framework built with **Java**, **Selenium**, and **TestNG**, designed to validate the uniform distribution hypothesis for dice rolls on [random.org](https://www.random.org/dice/?num=1).

---

## 🎯 Challenge

> **Hypothesis:** For a large number of rolls (> 1000), the distribution of dice results strives toward a uniform distribution.

**Requirements:** Tests must verify that the maximum deviation of dice results is within 5%.

- **Test 1:** Single dice (`num=1`)
- **Test 2:** Two dice (`num=2`) — total roll points is the sum of both dice

**💡 Hints:**
- Try 1000, 5000, and 10000 rolls to observe how distribution changes
- Think about the most efficient way to implement the solution

**⭐ Bonus:** Draw a chart to visualize dice result deviation in each test — implemented as an ASCII bar chart printed to the console at the end of each test.

---

## 🛠️ Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Language |
| Selenium | 4.27.0 | Browser automation |
| TestNG | 7.10.2 | Test runner |
| Maven | — | Build & dependency management |
| Selenium Manager | built-in | Auto-downloads the correct ChromeDriver |

---

## ⚙️ Setup

### Prerequisites

- Java 17+
- Maven
- Google Chrome installed

### ChromeDriver

No manual setup needed. Selenium 4.6+ includes **Selenium Manager**, which automatically downloads the correct ChromeDriver for your installed Chrome version and operating system at runtime.

---

## ▶️ Running Tests

```bash
# From the project root
mvn package
```

On Windows, if the ASCII chart renders incorrectly in Command Prompt, run:

```bash
mvn package -Dfile.encoding=UTF-8
```

---

## 🗂️ Project Structure

```
SDET-Test-2/
├── src/
│   ├── main/java/
│   │   ├── helpers/                # Utilities (WebDriver, screenshots)
│   │   ├── pages/                  # Page Object Model classes
│   │   └── tools/                  # ANSI console color helpers
│   └── test/java/tests/            # Test classes
│       ├── BaseTest.java
│       └── DiceRollerTest.java     # Main tests: oneDiceTest, twoDicesTest
├── .gitignore
└── pom.xml
```

---

## 👩‍💻 Author

**Carolina Olguin** — Senior QA Engineer · [GitHub](https://github.com/olguinc) · [LinkedIn](https://www.linkedin.com/in/carolina-olg/)

> *"The best tests don't just find bugs — they document the expected behavior of the system."*
