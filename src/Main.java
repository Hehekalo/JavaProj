import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите выражение: ");
            String line = scanner.nextLine();
            if(line.equals("end")) break;
            System.out.println(calc(line));
        }
    }

    public static String calc(String input)
    {
        return Integer.toString(Calculus.StringCalculus(input));
    }


    static  class Calculus{
        static byte theFirstTerm, theSecondTerm;
        static char operator;
        static final byte lowerLimit=0, upperLimit=11;

        public static int StringCalculus(String input){
            parser(input);
            return switch (operator) {
                case '+' -> theFirstTerm + theSecondTerm;
                case '-' -> theFirstTerm - theSecondTerm;
                case '*' -> theFirstTerm * theSecondTerm;
                case '/' -> theFirstTerm / theSecondTerm;
                default -> 403;
            };
        }

        private static void parser(String input){
            input = input.replaceAll(" ", "");
            try {

                if (!input.matches("^\\d*[+\\-*/]\\d*$")) throw new Exception("InvalidFormat");

                String[] subString = input.split("((?<=[+\\-*/])|(?=[+\\-*/]))");

                if (subString.length !=3) throw new Exception("Incorrect number of operands");
                
                theFirstTerm = Byte.parseByte(subString[0]);
                operator = subString[1].charAt(0);
                theSecondTerm = Byte.parseByte(subString[2]);

                if (!((lowerLimit < theFirstTerm && theFirstTerm < upperLimit) &&
                        (lowerLimit < theSecondTerm && theSecondTerm < upperLimit)))
                    throw new Exception("Operands are not in the interval");
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
                System.out.println("Формат математической операции не удовлетворяет заданию " +
                        "- два числовых операнда в пределах (" + lowerLimit+":" + upperLimit + ") и один оператор (+, -, /, *)");
                System.exit(-1);
            }
        }
    }
}
