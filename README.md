# Appium Scroll Utils

A lightweight Java utility library for advanced scroll and swipe gestures in Appium/Selenium mobile test automation.

![License](https://img.shields.io/badge/license-MIT-green)

---

## ✨ Features
✅ Simple APIs to perform swipes in any direction  
✅ Adjustable swipe speeds  
✅ Supports repeating swipes multiple times  
✅ Minimal dependencies

---

## 📦 Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>io.github.ahmedraafat2</groupId>
  <artifactId>appium-scroll-utils</artifactId>
  <version>1.0.0</version>
</dependency>
```

---

## 🛠️ Usage

Create an instance of the `Swipe` class and use the provided methods.

```java
// Example usage:

Swipe swipe = new Swipe(driver);

// Swipe right at normal speed
swipe.swipe(Swipe.Directions.RIGHT, Swipe.Speed.NORMAL);

// Swipe up quickly, 3 times
swipe.swipeMultiple(Swipe.Directions.UP, Swipe.Speed.FAST, 3);
```

---

## 🧩 API Reference

### `Swipe` class

#### Methods

🔹 **swipe(Directions direction, Speed speed)**  
Performs a single swipe in the given direction and speed.

| Parameter | Type | Description |
|-----------|------|-------------|
| `direction` | Directions | The swipe direction |
| `speed` | Speed | The swipe speed |

---

🔹 **swipeMultiple(Directions direction, Speed speed, int repeatCount)**  
Performs multiple swipes sequentially.

| Parameter | Type | Description |
|-----------|------|-------------|
| `direction` | Directions | The swipe direction |
| `speed` | Speed | The swipe speed |
| `repeatCount` | int | Number of repetitions |

---

#### Enums

**Directions**
```java
LEFT
RIGHT
UP
DOWN
```

**Speed**
```java
VERY_FAST
FAST
NORMAL
SLIGHTLY_SLOW
SLOW
```

---

## 📝 License
MIT License – see [LICENSE](https://opensource.org/licenses/MIT) file for details.

---

## 💬 Contact
For questions or issues, please open an issue on the [GitHub repository](https://github.com/AhmedRaafat2/appium-scroll-utils).
