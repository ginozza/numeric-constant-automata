enum State {
    Q0,
    Q1,
    Q2,
    Q3,
    Q4,
    Q6
}

public class Main {

    public static boolean isValidNumber(String input) {
        State state = State.Q0;

        for (char c : input.toCharArray()) {
            switch (state) {
                case Q0:
                    if (Character.isDigit(c) || c == '+' || c == '-') state = State.Q1;
                    else return false;
                    break;
                case Q1:
                    if (c == '.') state = State.Q2;
                    else if (Character.isDigit(c)) state = State.Q1;
                    else if (c == 'E' || c == 'e') state = State.Q4;
                    else return false;
                    break;
                case Q2:
                    if (Character.isDigit(c)) state = State.Q3;
                    else if (c == 'E' || c == 'e') state = State.Q4;
                    else return false;
                    break;
                case Q3:
                    if (Character.isDigit(c)) state = State.Q3;
                    else if (c == 'E' || c == 'e') state = State.Q4;
                    else return false;
                    break;
                case Q4:
                    if (Character.isDigit(c)) state = State.Q6;
                    else if (c == '+' || c == '-') state = State.Q6;
                    else return false;
                    break;
                case Q6:
                    if (Character.isDigit(c)) state = State.Q6;
                    else return false;
                    break;
                default:
                    return false;
            }
        }

        return state == State.Q1 || state == State.Q3 || state == State.Q6;
    }

    public static void main(String[] args) {
        String[] testCases = {
            "123", // válido 
            "+.456", // válido 
            "123.456", // válido 
            "+.456E2", // válido 
            "123E-4", // válido 
            "12E2.1", // inválido 
            "12E", // inválido 
            ".E2", // inválido 
            "12E+4", // válido 
            "12E-34", // válido 
            "12E4E5" // inválido 
        };

        for (String test : testCases) {
            System.out.println(test + ": " + isValidNumber(test));
        }
    }
}

