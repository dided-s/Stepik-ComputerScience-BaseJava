package theme_4_1;

public class GetCallerClassAndMethodName {

    public static String getCallerClassAndMethodName() {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();

        StringBuilder answer = new StringBuilder();
        if (elements.length >= 3) {
            answer.append(elements[2].getClassName());
            answer.append('#');
            answer.append(elements[2].getMethodName());

            return answer.toString();
        }

        return null;
    }
}