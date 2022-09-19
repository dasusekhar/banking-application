package com.example.Banking.application1;

import com.example.Banking.application1.entity.MyBank;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class PdfUtil {
  private List<MyBank> userList;

  public PdfUtil(List<MyBank>userList) {
    this.userList=userList;
  }

  public void DataToPdf(HttpServletResponse response) throws IOException {
  Document document=new Document(PageSize.A4);
  PdfWriter .getInstance(document,response.getOutputStream());
  document.open();
  document.add(new Paragraph("LIST OF CUSTOMERS"));
  PdfPTable table=new PdfPTable(4);
document.add(table);
document.close();
}

   }

