package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {
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
        String pathInfo = req.getPathInfo();
        long productId = 0;

//        resp.getWriter().println("<h1>Привет от сервлета!!!</h1>");
//        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
//        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
//        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
//        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
//        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
//        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");

        if (pathInfo.equals("/productId")) {
            productId = Long.valueOf(req.getParameter("param1"));
//            resp.getWriter().println("<h1>Подробные данные по товару, id " + productId + "</h1>");
            printProductForId(wr, productId);
        } else {
            printAllProducts(wr);
        }

    }

    public void printAllProducts(PrintWriter wr) {
        wr.println("<h1>Список всех товаров</h1>");
        wr.println("<table width=\"80%\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\">");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Наименование</th>");
        wr.println("</tr>");

        for (Product product : productRepository.findAll()) {
            wr.println("<tr>");
            wr.println("<td align=\"center\"><strong><a href='" + "productId?param1=" + product.getId() + "'>"
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
}
