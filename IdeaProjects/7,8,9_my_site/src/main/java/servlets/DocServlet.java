package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.DoctorsRepository;
import interfaces.FilesService;
import models.Doctors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/insert")
@MultipartConfig
public class DocServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private DoctorsRepository doctorsRepository;
    private FilesService filesService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        doctorsRepository = (DoctorsRepository) config.getServletContext().getAttribute("doctorsRepository");
        this.filesService = (FilesService) config.getServletContext().getAttribute("filesUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/InsertDoctor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Doctors doctor = Doctors.builder()
                .name(request.getParameter("name"))
                .secondName(request.getParameter("secondName"))
                .lastName(request.getParameter("lastName"))
                .position(request.getParameter("position"))
                .exp(request.getParameter("exp"))
                .build();

        Part part = request.getPart("file");

        filesService.saveFileToStorage(
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());

        doctorsRepository.insert(doctor);        response.sendRedirect("/doctors");
    }
}
