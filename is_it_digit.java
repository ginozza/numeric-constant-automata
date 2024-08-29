public class NumberValidator {

    // Enumeración de estados
    enum State {
        Q0, Q1, Q2, Q3, Q4, Q6
    }

    // Método para validar el número
    public static boolean isValidNumber(String input) {
        State state = State.Q0;
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            switch (state) {
                case Q0:
                    if (c >= '0' && c <= '9') state = State.Q1;
                    else if (c == '+' || c == '-') state = State.Q1;
                    else return false;
                    break;
                case Q1:
                    if (c == '.') state = State.Q2;
                    else if (c >= '0' && c <= '9') state = State.Q3;
                    else if (c == 'E' || c == 'e') state = State.Q4;
                    else return false;
                    break;
                case Q2:
                    if (c >= '0' && c <= '9') state = State.Q3;
                    else if (c == 'E' || c == 'e') state = State.Q4;
                    else if (i == length - 1) state = State.Q3; // Fin de la cadena
                    else return false;
                    break;
                case Q3:
                    if (c >= '0' && c <= '9') state = State.Q3;
                    else if (c == 'E' || c == 'e') state = State.Q4;
                    else if (i == length - 1) return true; // Fin de la cadena
                    else return false;
                    break;
                case Q4:
                    if (c >= '0' && c <= '9') state = State.Q6;
                    else if (c == '+' || c == '-') state = State.Q6;
                    else return false;
                    break;
                case Q6:
                    if (c >= '0' && c <= '9') state = State.Q6;
                    else return false;
                    break;
                default:
                    return false;
            }
        }

        // Finalización válida si el estado es Q1, Q2, Q3 o Q6
        return state == State.Q1 || state == State.Q2 || state == State.Q3 || state == State.Q6;
    }

    public static void main(String[] args) {
        String[] testCases = {
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

        for (String testCase : testCases) {
            System.out.println(testCase + ": " + (isValidNumber(testCase) ? "true" : "false"));
        }
    }
}

