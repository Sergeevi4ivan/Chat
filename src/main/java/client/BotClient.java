package client;

import server.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {


    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);

            SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.YYYY");
            SimpleDateFormat dayFormat = new SimpleDateFormat("d");
            SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
            SimpleDateFormat yearFormat = new SimpleDateFormat("YYYY");
            SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss");
            SimpleDateFormat hourFormat = new SimpleDateFormat("H");
            SimpleDateFormat minuteFormat = new SimpleDateFormat("m");
            SimpleDateFormat secondFormat = new SimpleDateFormat("s");

            Calendar calendar = Calendar.getInstance();

            if (message.contains(":")) {
            String[] strings = message.split(":");
            String infoForUser = "Информация для " + strings[0] + ": ";

                switch (strings[1].trim()) {
                case "дата": sendTextMessage(infoForUser +  dateFormat.format(calendar.getTime()));
                    break;
                case "день": sendTextMessage(infoForUser + dayFormat.format(calendar.getTime()));
                    break;
                case "месяц": sendTextMessage(infoForUser + monthFormat.format(calendar.getTime()));
                    break;
                case "год": sendTextMessage(infoForUser + yearFormat.format(calendar.getTime()));
                    break;
                case "время": sendTextMessage(infoForUser + timeFormat.format(calendar.getTime()));
                    break;
                case "час": sendTextMessage(infoForUser + hourFormat.format(calendar.getTime()));
                    break;
                case "минуты": sendTextMessage(infoForUser + minuteFormat.format(calendar.getTime()));
                    break;
                case "секунды": sendTextMessage(infoForUser + secondFormat.format(calendar.getTime()));
                    break;

            }

            }
        }
    }
}
