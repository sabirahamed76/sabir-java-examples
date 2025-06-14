package com.home.java.utils.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfUtil {

	public static void concatPDFs(List<String> streamOfPDFFiles, String mergedPdfFile, boolean paginate) {

		OutputStream outputStream = null;
		Document document = new Document();
		PdfWriter writer = null;
		PdfContentByte cb = null;
		try {
			outputStream = new FileOutputStream(mergedPdfFile);
			writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			cb = writer.getDirectContent(); // Holds the PDF
			int currentPageNumber = 0;
			for (String aFile : streamOfPDFFiles) {
				InputStream is = null;
				PdfReader pdfReader = null;
				try {
					int pageOfCurrentReaderPDF = 0;
					is = new FileInputStream(aFile);
					pdfReader = new PdfReader(is);
					// Create a new page in the target for each source page.
					while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
						document.newPage();
						pageOfCurrentReaderPDF++;
						currentPageNumber++;
						PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
						cb.addTemplate(page, 0, 0);
					}
				} finally {
					if(is != null) {
						is.close();
					}
					if(pdfReader != null) {
						//pdfReader.close();
					}
				}
			}
			outputStream.flush();
			document.close();
		    outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null && document.isOpen()) {
				document.close();
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
				}
			}
			
		}
	}
}

//PREV CODE
/*public static void concatPDFs(List<String> streamOfPDFFiles, String mergedPdfFile, boolean paginate) {

	OutputStream outputStream = null;
    
    Document document = new Document();
    try {
      outputStream = new FileOutputStream(mergedPdfFile);	
      
      List<PdfReader> readers = new ArrayList<PdfReader>();
      //int totalPages = 0;

      // Create Readers for the pdfs.
      for (String aFile : streamOfPDFFiles){
    	InputStream is = new FileInputStream(aFile);  
        PdfReader pdfReader = new PdfReader(is);
        readers.add(pdfReader);
        //totalPages += pdfReader.getNumberOfPages();
      }
      // Create a writer for the outputstream
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

      document.open();
      BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
      PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
      // data

      PdfImportedPage page;
      int currentPageNumber = 0;
      int pageOfCurrentReaderPDF = 0;
      Iterator<PdfReader> iteratorPDFReader = readers.iterator();

      // Loop through the PDF files and add to the output.
      while (iteratorPDFReader.hasNext()) {
        PdfReader pdfReader = iteratorPDFReader.next();

        // Create a new page in the target for each source page.
        while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
          document.newPage();
          pageOfCurrentReaderPDF++;
          currentPageNumber++;
          page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
          cb.addTemplate(page, 0, 0);

          // Code for pagination.
          if (paginate) {
            cb.beginText();
            cb.setFontAndSize(bf, 9);
            //cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "" + currentPageNumber + " of " + totalPages, 520, 5, 0);
            cb.endText();
          }
        }
        pageOfCurrentReaderPDF = 0;
      }
      outputStream.flush();
      document.close();
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (document.isOpen())
        document.close();
      try {
        if (outputStream != null)
          outputStream.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

}*/