package theme_2_4;

public class PrintTextPerRole {

    public static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder[] rolesTextLines = new StringBuilder[roles.length];
        int rolesTextLinesIndex = 1;

        // Инициализируем rolesTextLines[] строками вида: Артемий Филиппович:\n
        for (int i = 0; i < roles.length; i++) {
            rolesTextLines[i] = new StringBuilder(roles[i] + ":\n");
        }

        for (String textLine : textLines) {

            // Ищем первый ":" и делим строку на role и text
            String role = textLine.substring(0, textLine.indexOf(":"));
            String text = textLine.substring(textLine.indexOf(":") + 1);
            int roleIndex = getRoleIndex(roles, role);

            rolesTextLines[roleIndex]
                    .append(rolesTextLinesIndex)
                    .append(")")
                    .append(text)
                    .append("\n");

            rolesTextLinesIndex++;
        }

        return getResult(rolesTextLines);
    }

    private static int getRoleIndex(String[] roles, String role) {
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].contentEquals(role))
                return i;
        }
        System.out.println(role);
        return -1;
    }

    private static String getResult(CharSequence[] lines) {
        StringBuilder result = new StringBuilder();
        for (CharSequence line : lines) {
            result.append(line).append("\n");
        }

        return result.toString();
    }
}