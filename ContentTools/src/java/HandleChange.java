/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author test
 */
@WebServlet(urlPatterns = {"/HandleChange"})
public class HandleChange extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
       
                ServletContext context = request.getServletContext();
               String firstPathPart = context.getRealPath("") + "/";
	       String secondPathPart = "assets/images";
               String path = firstPathPart + secondPathPart;
               String url = secondPathPart;
               long [][] size = new long[1][2];
               MultipartRequest mpr = new MultipartRequest(request, path);
               File file = null;
               if(mpr.getFile("image")!=null)
               {
                    file = mpr.getFile("image");
                    url = secondPathPart + "/" + file.getName();
                    System.out.println(file.getName() + " " + file.length());
               }
               else{
//                MultipartParser p = new MultipartParser(request, 1000);
//                Part part = null;
//                    while((part = p.readNextPart()) != null){
//                        ParamPart ppart = (ParamPart)part;
//                        System.out.println(ppart.getName() + " " + ppart.getStringValue());
//                              }
                     System.out.println(mpr.getParameter("main-content"));
               }
              
                   size[0][0] = 1000;
                   size[0][1] = 1000;
                JSONObject json = new JSONObject();
            try {
                if(mpr.getParameter("url")!=null)
                {
                    json.put("url", mpr.getParameter("url"));
                    System.out.println(mpr.getParameter("url"));
                }
                else{
                json.put("url", url);
                }
                  json.put("size", size);
            } catch (JSONException ex) {
                Logger.getLogger(HandleChange.class.getName()).log(Level.SEVERE, null, ex);
            }
                out.println(json.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
