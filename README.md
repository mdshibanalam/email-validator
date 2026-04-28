# 📧 Java Bulk Email Sanitizer

An efficient, custom-built ETL (Extract-Transform-Load) pipeline written in Java. This backend utility reads bulk email lists from text files, sanitizes the data using modular string-parsing algorithms, and outputs clean `.csv` files for business use.

## 🚀 Features
* **Zero External Dependencies:** Built entirely using core Java libraries.
* **Custom Parsing Engine:** Validates email structure without relying on Regex, proving deep understanding of String manipulation and $O(N)$ linear time complexity.
* **Robust Error Handling:** Implements rigorous `try-catch-finally` blocks to handle `IOExceptions` and prevent memory leaks.
* **Automated Data Splitting:** Automatically categorizes emails and generates `valid_emails.csv` and `invalid_emails.csv`.

## 🛠️ Tech Stack
* **Language:** Java (JDK 8+)
* **Core Concepts:** File I/O (`Scanner`, `FileWriter`), Exception Handling, String Manipulation, Boolean Logic Optimization.

## ⚙️ How It Works
1. The program targets an input file (e.g., `emails.txt`).
2. It scans the document line-by-line using `Scanner`.
3. The custom `isValidEmail()` algorithm filters the string against strict protocol rules (checking for double dots, invalid characters via the `Character` class, and proper `@` placement).
4. The validated data is routed via `FileWriter` to its respective output `.csv` file.

## 👨‍💻 Run It Locally
Ensure you have Java installed, then compile and run the script:
```bash
javac EmailSanitizer2.java
java EmailSanitizer2
