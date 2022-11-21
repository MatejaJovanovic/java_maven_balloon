package Servlet;

import SolutionExamples.Solution;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
)
public class ReadWriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter pw = response.getWriter();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\jovanovicm\\IdeaProjects\\MavenBalloon\\src\\main\\Input_Output.txt"));
            Solution sol = new Solution();
            int output=sol.solution("lala",reader);
            File file = new File("C:\\Users\\jovanovicm\\IdeaProjects\\MavenBalloon\\src\\main\\Input_Output.txt");
            FileWriter fstream = new FileWriter(file,true);
            try (BufferedWriter out = new BufferedWriter(fstream)) {
                out.write("Total number of iterations is "+output+ "\n");
                        out.newLine();
            }
            pw.println("File is updated successfully");
        }

        catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}



