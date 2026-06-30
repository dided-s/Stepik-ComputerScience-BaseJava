package theme_4_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import theme_4_3.mail.MailMessage;
import theme_4_3.mail.MailPackage;
import theme_4_3.mail.Package;
import theme_4_3.mail.Sendable;
import tools.CustomAssertions;
import tools.StringHandler;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MailServicesTest {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    private static final Package package10 = new Package("Value 10", 10);
    private static final Package package20 = new Package("Value 20", 20);
    private static final Package stones = new Package("stones", 10);
    private static final Package weapons = new Package(WEAPONS, 20);
    private static final Package bannedSubstance = new Package(BANNED_SUBSTANCE, 20);

    private static final MailMessage mailMessage = new MailMessage("name1", "name2", "message1");
    private static final MailMessage austinMailMessage = new MailMessage(AUSTIN_POWERS, "name1", "message2");

    private static final MailPackage mailPackage = new MailPackage("name2", "name1", package10);
    private static final MailPackage austinMailPackage = new MailPackage(AUSTIN_POWERS, "name1", package20);
    private static final MailPackage stonesMailPackage = new MailPackage("name2", "name1", stones);
    private static final MailPackage weaponsMailPackage = new MailPackage("name1", "name3", weapons);
    private static final MailPackage bannedSubstanceMailPackage = new MailPackage("name2", "name3", bannedSubstance);


    @Test
    @DisplayName("Проверка Spy")
    void testSpy() {
        Logger logger = Logger.getLogger(MailServicesTest.class.getName());
        StringHandler handler = new StringHandler();
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        MailServices.Spy spy = new MailServices.Spy(logger);

        Sendable mail1 = spy.processMail(mailMessage);
        String log1 = handler.getLog();
        Assertions.assertEquals(mailMessage, mail1);
        CustomAssertions.assertContains(log1, "Usual correspondence: from name1 to name2");
        CustomAssertions.assertContains(log1, "INFO");
        handler.clear();

        Sendable mail2 = spy.processMail(austinMailMessage);
        String log2 = handler.getLog();
        Assertions.assertEquals(austinMailMessage, mail2);
        CustomAssertions.assertContains(log2, "Detected target mail correspondence: from Austin Powers to name1 \"message2\"");
        CustomAssertions.assertContains(log2, "WARNING");
        handler.clear();

        Sendable mail3 = spy.processMail(mailPackage);
        String log3 = handler.getLog();
        Assertions.assertEquals(mailPackage, mail3);
        Assertions.assertEquals("", log3.trim());
        handler.clear();

        Sendable mail4 = spy.processMail(austinMailPackage);
        String log4 = handler.getLog();
        Assertions.assertEquals(austinMailPackage, mail4);
        Assertions.assertEquals("", log4.trim());
        logger.addHandler(handler);
    }

    @Test
    @DisplayName("Проверка Thief")
    void testThief() {
        MailServices.Thief thief1 = new MailServices.Thief(10);
        MailServices.Thief thief2 = new MailServices.Thief(20);

        // Проверить, что вор проигнорирует message
        Sendable mail1 = thief1.processMail(mailMessage);
        Assertions.assertEquals(mailMessage, mail1);

        // Проверить, что вор с minCost 10 украдет посылку с 10 price
        MailPackage mail2 = (MailPackage) thief1.processMail(mailPackage);
        Assertions.assertNotEquals(mailPackage, mail2);
        Assertions.assertEquals(10, thief1.getStolenValue());
        Assertions.assertEquals(0, mail2.getContent().getPrice());
        CustomAssertions.assertContains(mail2.getContent().getContent(), "stones instead of Value 10");

        // Проверить, что вор с minCost 20 проигнорирует посылку с 10 price
        MailPackage mail3 = (MailPackage) thief2.processMail(mailPackage);
        Assertions.assertEquals(mailPackage, mail3);
    }

    @Test
    @DisplayName("Проверка Inspector")
    void testInspector() {
        MailServices.Inspector inspector = new MailServices.Inspector();

        Assertions.assertDoesNotThrow(() -> inspector.processMail(mailMessage));
        Assertions.assertThrows(MailServices.IllegalPackageException.class,
                () -> inspector.processMail(weaponsMailPackage));
        Assertions.assertThrows(MailServices.IllegalPackageException.class,
                () -> inspector.processMail(bannedSubstanceMailPackage));
        Assertions.assertThrows(MailServices.StolenPackageException.class,
                () -> inspector.processMail(stonesMailPackage));
    }

    @Test
    @DisplayName("Проверка UntrustworthyMailWorker")
    void testUntrustworthyMailWorker() {
        Logger logger = Logger.getLogger(MailServicesTest.class.getName());
        StringHandler handler = new StringHandler();
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        MailServices.Spy spy = new MailServices.Spy(logger);
        MailServices.Thief thief1 = new MailServices.Thief(10);
        MailServices.Thief thief2 = new MailServices.Thief(20);
        MailServices.Inspector inspector = new MailServices.Inspector();

        MailServices.UntrustworthyMailWorker untrustworthyMailWorkerWithInspector = new MailServices.UntrustworthyMailWorker(
                spy, thief2, thief1, inspector);

        Sendable mail1 = untrustworthyMailWorkerWithInspector.processMail(mailMessage);
        String log1 = handler.getLog();
        Assertions.assertEquals(mailMessage, mail1);
        CustomAssertions.assertContains(log1, "Usual correspondence: from name1 to name2");
        CustomAssertions.assertContains(log1, "INFO");
        handler.clear();

        Sendable mail2 = untrustworthyMailWorkerWithInspector.processMail(austinMailMessage);
        String log2 = handler.getLog();
        Assertions.assertEquals(austinMailMessage, mail2);
        CustomAssertions.assertContains(log2, "Detected target mail correspondence: from Austin Powers to name1 \"message2\"");
        CustomAssertions.assertContains(log2, "WARNING");
        handler.clear();

        // Украденную посылку нашли, но вор все равно получил stolenValue
        Assertions.assertThrows(MailServices.StolenPackageException.class,
                () -> untrustworthyMailWorkerWithInspector.processMail(mailPackage));
        Assertions.assertEquals(10, thief1.getStolenValue());
        Assertions.assertEquals(0, thief2.getStolenValue());
    }
}