import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

    private Map<String,String> books = new HashMap<String,String>();

    @Override
    public void init(ServletConfig config) throws ServletException {
       books.put("C#", "700");
       books.put("C++", "1000");
       books.put("JAVA", "750");
       books.put("PHP", "590");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String lang = request.getParameter("lang");
        String price = books.get(lang).toString();
        response.setContentType("text/html,charset=utf-8");
        Cookie cookie = new Cookie(lang,price);
        response.addCookie(cookie);
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Insert title here</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "<p>Вы выбрали книгу по изучению " + lang + " по цене " + price + "</p>" +
                "<a href='http://localhost:8081/Lesson2_war_exploded/'>Назад</a>" +
                "  </body>\n" +
                "</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Insert title here</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "<p>Список книг, которые вы выбрали:" + "</p>");
        for (int i = 0; i < cookies.length; i++) {
            out.write("<p>" + cookies[i].getName()  + " | " + cookies[i].getValue() + "</p>");
        }
        out.write("  </body>\n" +
                "</html>");
        out.close();
    }
}
