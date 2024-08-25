#[derive(Debug, PartialEq)]
enum State {
    Q0,
    Q1,
    Q2,
    Q3,
    Q4,
    Q6,
}

fn is_valid_number(input: &str) -> bool {
    let mut state = State::Q0;

    for c in input.chars() {
        state = match (state, c) {
            (State::Q0, '0'..='9') | (State::Q0, '+') | (State::Q0, '-') => State::Q1,
            (State::Q1, '.') => State::Q2,
            (State::Q1, '0'..='9') => State::Q1,
            (State::Q1, 'E') | (State::Q1, 'e') => State::Q4,
            (State::Q2, '0'..='9') => State::Q3,
            (State::Q2, 'E') | (State::Q2, 'e') => State::Q4,
            (State::Q3, '0'..='9') => State::Q3,
            (State::Q3, 'E') | (State::Q3, 'e') => State::Q4,
            (State::Q4, '0'..='9') => State::Q6,
            (State::Q4, '+') | (State::Q4, '-') => State::Q6,
            (State::Q6, '0'..='9') => State::Q6,
            _ => return false, // Handle any input that doesn't match the previous cases
        }
    }

    // Verify if the final state is acceptable
    matches!(state, State::Q1 | State::Q3 | State::Q6)
}

fn main() {
    let test_cases = vec![
        "123", // valid 
        "+.456", // valid 
        "123.456", // valid 
        "+.456E2", // valid 
        "123E-4", // valid 
        "12E2.1", // invalid 
        "12E", // invalid 
        ".E2", // invalid 
        "12E+4", // valid 
        "12E-34", // valid 
        "12E4E5", // invalid 
    ];

    for test in test_cases {
        println!("{}: {}", test, is_valid_number(test));
    }
}
