fn is_valid_number(input: &str) -> bool {
    #[derive(Debug, PartialEq)]
    enum State {
        Q0,
        Q1,
        Q2,
        Q3,
        Q4,
        Q6,
    }

    let mut state = State::Q0;

    for c in input.chars() {
        match state {
            State::Q0 => {
                if c.is_digit(10) {
                    state = State::Q1;
                } else if c == '+' || c == '-' {
                    state = State::Q1;
                } else {
                    return false;
                }
            }
            State::Q1 => {
                if c == '.' {
                    state = State::Q2;
                } else if c.is_digit(10) {
                    state = State::Q3;
                } else if c == 'E' || c == 'e' {
                    state = State::Q4;
                } else {
                    return false;
                }
            }
            State::Q2 => {
                if c.is_digit(10) {
                    state = State::Q3;
                } else if c == 'E' || c == 'e' {
                    state = State::Q4;
                } else if c == '\0' {
                    state = State::Q3; // Accept end of input here
                } else {
                    return false;
                }
            }
            State::Q3 => {
                if c.is_digit(10) {
                    state = State::Q3;
                } else if c == 'E' || c == 'e' {
                    state = State::Q4;
                } else if c == '\0' {
                    return true; // Accept end of input here
                } else {
                    return false;
                }
            }
            State::Q4 => {
                if c.is_digit(10) {
                    state = State::Q6;
                } else if c == '+' || c == '-' {
                    state = State::Q6;
                } else {
                    return false;
                }
            }
            State::Q6 => {
                if c.is_digit(10) {
                    state = State::Q6;
                } else {
                    return false;
                }
            }
        }
    }

    // Valid end states
    matches!(state, State::Q1 | State::Q2 | State::Q3 | State::Q6)
}

fn main() {
    let test_cases = [
        "123",       // válido 
        "+.456",     // válido 
        "123.456",   // válido 
        "+.456E2",   // válido 
        "123E-4",    // válido 
        "12E2.1",    // inválido 
        "12E",       // inválido 
        ".E2",       // inválido 
        "12E+4",     // válido 
        "12E-34",    // válido 
        "12E4E5",    // inválido 
        "1."         // válido
    ];

    for &test_case in &test_cases {
        println!("{}: {}", test_case, if is_valid_number(test_case) { "true" } else { "false" });
    }
}

