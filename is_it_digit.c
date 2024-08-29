#include <stdio.h>
#include <stdbool.h>

typedef enum {
    Q0,
    Q1,
    Q2,
    Q3,
    Q4,
    Q6
} State;

bool is_valid_number(const char *input) {
    State state = Q0;

    while (*input) {
        char c = *input;
        switch (state) {
            case Q0:
                if (c >= '0' && c <= '9') state = Q1;
                else if (c == '+' || c == '-') state = Q1;
                else return false;
                break;
            case Q1:
                if (c == '.') state = Q2;
                else if (c >= '0' && c <= '9') state = Q3;
                else if (c == 'E' || c == 'e') state = Q4;
                else return false;
                break;
            case Q2:
                if (c >= '0' && c <= '9') state = Q3;
                else if (c == 'E' || c == 'e') state = Q4;
                else if (c == '\0') state = Q3; 
                else return false;
                break;
            case Q3:
                if (c >= '0' && c <= '9') state = Q3;
                else if (c == 'E' || c == 'e') state = Q4;
                else if (c == '\0') return true; 
                else return false;
                break;
            case Q4:
                if (c >= '0' && c <= '9') state = Q6;
                else if (c == '+' || c == '-') state = Q6;
                else return false;
                break;
            case Q6:
                if (c >= '0' && c <= '9') state = Q6;
                else return false;
                break;
            default:
                return false;
        }
        input++;
    }

    // Finalización válida si el estado es Q1, Q2, Q3 o Q6
    return state == Q1 || state == Q2 || state == Q3 || state == Q6;
}

int main() {
    const char *test_cases[] = {
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
    };

    for (int i = 0; i < sizeof(test_cases)/sizeof(test_cases[0]); i++) {
        printf("%s: %s\n", test_cases[i], is_valid_number(test_cases[i]) ? "true" : "false");
    }

    return 0;
}

