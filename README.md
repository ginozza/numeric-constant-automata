# Number Validation with Deterministic Finite Automaton (DFA)

This project implements a deterministic finite automaton (DFA) in Rust, C, and Java to validate strings representing numbers in various formats. The automaton can recognize integers, decimals, and numbers in scientific notation.

## Features

- **Implemented Languages**: Rust, C, and Java.
- **Patterns Recognized**:
  - Integers (e.g., "123", "-456")
  - Decimals (e.g., "123.456", "-.789")
  - Scientific notation (e.g., "1.23E4", "-4.56e-7")
- **States and Transitions:**
  - The DFA uses 6 states (Q0, Q1, Q2, Q3, Q4, Q6) to evaluate the input string.
  - The DFA accepts transitions based on specific characters like digits (0-9), signs (+ or -), decimal point (.), and exponent (E or e).
- **Validation:** The automaton ensures the entire string follows a valid structure before accepting it as a valid number.

## Examples

### Valid Inputs

- "123"
- "+.456"
- "123.456"
- "+.456E2"
- "123E-4"

### Invalid Inputs

- "12E2.1"
- "12E"
- ".E2"
- "12E4E5"

## Architecture

### Automaton States

- Q0: Initial state, waiting for a sign or digit.
- Q1: State that accepts integer digits.
- Q2: State that accepts the decimal point.
- Q3: State that accepts digits after the decimal point.
- Q4: State that accepts E or e for scientific notation.
- Q6: State that accepts exponent digits after E or e.

## Contributions

This repository is an activity for a University Compilers class. I'm a student, so feel free to open issues or pull requests to improve this project.

## License

This project is licensed under the MIT License. See the (LICENSE)[https://github.com/ginozza/is_it_digit/blob/main/LICENSE] file for details.

