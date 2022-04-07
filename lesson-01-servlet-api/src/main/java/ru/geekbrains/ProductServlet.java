package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.add(new Product("Компьютер HP D600", 45000));
        this.productRepository.add(new Product("Компьютер HP D600", 45000));
        this.productRepository.add(new Product("Компьютер HP D600", 45000));
        this.productRepository.add(new Product("Компьютер HP D600", 45000));
        this.productRepository.add(new Product("Монитор LG G2100", 12300));
        this.productRepository.add(new Product("Монитор LG G2100", 12300));
        this.productRepository.add(new Product("Монитор LG G2100", 12300));
        this.productRepository.add(new Product("Монитор LG G2100", 12300));
        this.productRepository.add(new Product("Ноутбук Dell Vostro 650", 87950));
        this.productRepository.add(new Product("Ноутбук Dell Vostro 650", 87950));
        this.productRepository.add(new Product("Ноутбук Dell Vostro 650", 87950));
        this.productRepository.add(new Product("Телевизор Sumsung DS5600D", 76000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter wr = resp.getWriter();

        if(req.getPathInfo() == null || req.getPathInfo().equals("/")){
            printAllProducts(wr);
        } else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if(matcher.matches()){
                Long id = Long.parseLong(matcher.group(1));
                Product product = this.productRepository.FindById(id);
                if (product == null){
                    resp.getWriter().println("Product not found");
                    resp.setStatus(404);
                    return;
                }
                printProductForId(wr, id);
            } else{
                resp.getWriter().println("Bad parameters...");
                resp.setStatus(400);
            }

        }
    }

    public void printAllProducts(PrintWriter wr) {
        wr.println("<h1>Список всех товаров</h1>");
        wr.println("<table width=\"80%\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\">");
        wr.println("<tr>");
        tableHeader(wr, "Id");
        tableHeader(wr, "Наименование товаров");
//        wr.println("<th>Id</th>");
//        wr.println("<th>Наименование</th>");
        wr.println("</tr>");

        for (Product product : productRepository.findAll()) {
            wr.println("<tr>");
            wr.println("<td align=\"center\"><strong><a href='" + product.getId() + "'>"
                    + product.getId() + "</a></strong></td>");
            wr.println("<td>" + product.getTitle() + "</td>");
            wr.println("</tr>");
        }

        wr.println("</table>");
    }

    public void printProductForId(PrintWriter wr, long id) {
        Product product = productRepository.FindById(id);
        wr.println("<h1>Подробные данные по товару, № " + product.getId() + "</h1>");
        wr.println("<table width=\"50%\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\">");

        wr.println("<tr>");
        wr.println("<td><strong>Номер</strong></td>");
        wr.println("<td>" + product.getId() + "</td>");
        wr.println("</tr>");

        wr.println("<tr>");
        wr.println("<td><strong>Наименование</strong></td>");
        wr.println("<td>" + product.getTitle() + "</td>");
        wr.println("</tr>");

        wr.println("<tr>");
        wr.println("<td><strong>Стоимость</strong></td>");
        wr.println("<td>" + product.getCost() + "</td>");
        wr.println("</tr>");

        wr.println("</table>");
    }

    public void tableHeader(PrintWriter wr, String title){
        wr.printf("<th>%s%n</th>\n", title);
    }
}
