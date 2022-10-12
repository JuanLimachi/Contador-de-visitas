package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContadorServlet", urlPatterns = {"/ContadorServlet"})
public class ContadorServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contador = 0;
        
        //  Obtener el arreglo de cookies del cliente
        Cookie[] cukis = request.getCookies();
        
        if(cukis != null){
            for(Cookie c : cukis){
                if(c.getName().equals("visitas")){
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }
        contador++;
        
        Cookie c = new Cookie("visitas", Integer.toString(contador));
        
        //Establece el tiempo de la cookie en segundos
        c.setMaxAge(300);
        response.addCookie(c);
        
        //  Generar contenido a partir del servlet
        response.setContentType("text/httml");
        PrintWriter out = response.getWriter();
        out.print("Visitante N°: " + contador);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
