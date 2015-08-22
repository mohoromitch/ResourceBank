package ca.ryerson.scs.cscu.abstractClasses;

import ca.ryerson.scs.cscu.enums.Semester;

import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by mitchellmohorovich on 15-08-22.
 * A collection of common elements that all uploaded documents will share, time, files and their respective functions.
 */
public abstract class TimeDocumentHandler {
    private short year;
    private Semester semester;
    private Part uploadedFile;
    private String contentType;

    protected final int MINIMUM_YEAR = 1995;

    protected byte[] toByteArray(Part file) {
        InputStream input;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            input = file.getInputStream();
            byte[] buffer = new byte[1024];
            for (int length = 0; (length = input.read(buffer)) > 0; output.write(buffer, 0, length)) ;
        } catch (IOException e) {
            return null;
        }
        return output.toByteArray();
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public int[] getYearRange() {
        int size = this.getCurrentYear() - this.MINIMUM_YEAR + 1;
        int returnArray[] = new int[size];
        returnArray[0] = this.MINIMUM_YEAR;
        for (int i = 1; i < size; i++) {
            returnArray[i] = returnArray[i-1]+1;
        }
        return returnArray;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
        this.setContentType(this.getUploadedFile().getContentType());
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Semester[] getSemesters() {
        return Semester.values();
    }

}
