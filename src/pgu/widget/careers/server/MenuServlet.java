package pgu.widget.careers.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(getMenu());
    }

    public static String getMenu() {
        final String menuJson = "" + //
                "{'entries':                                " + //
                "           [                               " + //
                "            {                              " + //
                "              'code':'0'                   " + //
                "             ,'title':'Jobs'               " + //
                "            }                              " + //
                "           ]                               " + //
                "}                                          " + //
                ""; //

        return menuJson //
                .replaceAll("'", "\"") //
                .replaceAll("\\s", "") //
                ;
    }

}
