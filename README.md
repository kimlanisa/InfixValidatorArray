# Infix Validator Array

This project is a simple Java-based implementation that converts and evaluates infix mathematical expressions into postfix and prefix notations using the **stack** data structure, implemented with both **arrays** and **linked lists**. It was developed to fulfill a course assignment.

## Assignment Overview

### ✅ Infix Notation Validation

The program prompts the user to input an **infix expression**. It validates the expression to ensure it follows correct mathematical syntax.

Examples:
- ✔️ `5 + 4 / 5` → valid
- ❌ `5 * 5 +` → invalid

### 🔁 Conversion to Postfix & Prefix

Once validated, the program converts the infix expression into:
- **Postfix notation**, e.g., `545/+`
- **Prefix notation**, e.g., `+/455`

Both conversions are done using **stack-based algorithms**.

### 🧮 Expression Evaluation

The converted postfix and prefix expressions are **evaluated** using stack operations to produce the final result of the mathematical expression.

### 🖥️ Displaying Results in Java

All outputs—including validation results, converted notations, and final evaluations—are displayed to the screen using the **Java** programming language.

## Project Structure

- `InfixValidatorArray.java`: Module to validate infix expressions using an array-based stack.
- `InfixValidatorArrayConverter.java`: Module to convert infix to postfix/prefix and evaluate the expressions.
- *(Optional)* `InfixValidatorLinkedList.java` and `InfixValidatorLinkedListConverter.java` if a linked list version is implemented.
