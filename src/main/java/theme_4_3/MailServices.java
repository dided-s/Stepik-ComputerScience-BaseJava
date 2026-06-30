package theme_4_3;

import theme_4_3.mail.Package;
import theme_4_3.mail.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MailServices {

    public static class UntrustworthyMailWorker implements MailService {

        private final MailService realMailService = new RealMailService();
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService... mailServices) {
            this.mailServices = mailServices;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            for (MailService mailService : mailServices) {
                mail = mailService.processMail(mail);
            }
            return getRealMailService().processMail(mail);
        }

        public MailService getRealMailService() {
            return realMailService;
        }
    }

    public static class Spy implements MailService {

        public static final String AUSTIN_POWERS = "Austin Powers";

        private Logger logger;
        private String target;

        public Spy(Logger logger) {
            this.logger = logger;
            this.target = AUSTIN_POWERS;
        }

        public Spy(Logger logger, String target) {
            this.logger = logger;
            this.target = target;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                if (mail.getFrom().equals(target) || mail.getTo().equals(target)) {
                    String message = ((MailMessage) mail).getMessage();
                    logger.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                            new Object[]{mail.getFrom(), mail.getTo(), message});
                } else {
                    logger.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                            new Object[]{mail.getFrom(), mail.getTo()});
                }
            }
            return mail;
        }
    }

    public static class Thief implements MailService {

        private int minCost;
        private int stolenValue;

        public Thief(int minCost) {
            this.minCost = minCost;
            this.stolenValue = 0;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                Package pack = mailPackage.getContent();
                int mailPackagePrice = pack.getPrice();

                if (mailPackagePrice >= minCost) {
                    stolenValue += mailPackagePrice;
                    Package stones = new Package("stones instead of " + pack.getContent(), 0);
                    return new MailPackage(mailPackage.getFrom(), mailPackage.getTo(), stones);
                }
            }

            return mail;
        }

        public int getStolenValue() {
            return stolenValue;
        }
    }

    public static class Inspector implements MailService {

        public static final String[] illegals = {"weapons", "banned substance"};
        public static final String[] stolens = {"stones"};

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                String content = mailPackage.getContent().getContent();

                for (String illegal : illegals) {
                    if (content.contains(illegal)) {
                        throw new IllegalPackageException(content);
                    }
                }

                for (String stone : stolens) {
                    if (content.contains(stone)) {
                        throw new StolenPackageException(content);
                    }
                }
            }

            return mail;
        }
    }

    public static class IllegalPackageException extends RuntimeException {

        public IllegalPackageException() {
        }

        public IllegalPackageException(String message) {
            super(message);
        }
    }

    public static class StolenPackageException extends RuntimeException {

        public StolenPackageException() {
        }

        public StolenPackageException(String message) {
            super(message);
        }
    }
}