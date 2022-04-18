package CopyURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Copier {


    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean ok;
            do {
                String url = readStringURL(bufferedReader);
                ok = readContent(url);
            } while (!ok);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Чтение документа по URL и вывод в консоль
     * @param URL
     */
    public static boolean readContent(String URL) {
        URL url = null;
        try {
            url = convertStringToURL(URL);
        } catch (MalformedURLException e) {
            System.out.println("Incorrect URL format. Try again");
            return false;
        }
        BufferedReader input = null;
        try {
            input = getReader(url);
        } catch (IOException e) {
            System.out.println("Error open connection to " + url + " Try another URL");
            return false;
        }
        String temp;
        try {
            while((temp = input.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * Чтение URL в формате String
     * @param bufferedReader - откуда читать
     * @return String url
     */
    public static String readStringURL(BufferedReader bufferedReader) {
        String url = null;
        try {
            do {
                System.out.println("Input URL to copy from");
                url = bufferedReader.readLine();
            } while (url == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Конвертация string в URL
     * @param stringURL - URL в String формате
     * @return URL url
     * @throws  MalformedURLException - если формат введенного URL не соответствует
     */
    public static URL convertStringToURL(String stringURL) throws MalformedURLException {
        URL url = new URL(stringURL);
        return url;
    }

    /**
     * Открывает поток для чтения содержимого по URL
     * @param url
     * @return BufferedReader
     * @throws IOException в случае, если не получается открыть поток
     */
    public static BufferedReader getReader(URL url) throws IOException {
        BufferedReader br;
            do {
                    //br = new BufferedReader(new InputStreamReader(url.openStream()));
                    HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                    httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
                    br = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
            } while (br == null);
        return br;
    }
}
