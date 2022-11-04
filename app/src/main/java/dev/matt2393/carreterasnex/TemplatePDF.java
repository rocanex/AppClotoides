package dev.matt2393.carreterasnex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class TemplatePDF {
    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fSubtitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.RED);

    String TAG = "ERROR ";

    public TemplatePDF(Context context) {
        this.context = context;

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void openDocument(String name){
        createFile(name);
        try {
            document = new Document(PageSize.A4.rotate());
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }
    }

    //val file = File("${Environment.getExternalStorageDirectory()}/$folderName")

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void createFile(String name){
        File folder = null;
        folder = new File(Environment.getExternalStorageDirectory().toString(), "CLOTOIDE");
        if(!folder.exists())
            folder.mkdir();
        pdfFile = new File(folder, name+".pdf");

    }

    public void closeDocument(){
        document.close();
    }

    public void addMetada(String title, String subjet, String author){
        document.addTitle(title);
        document.addSubject(subjet);
        document.addAuthor(author);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void addTitles(String title, String subTitle, String date){
        try {
            paragraph = new Paragraph();
            addChild(new Paragraph(title, fTitle));
            addChild(new Paragraph(subTitle, fSubtitle));
            addChild(new Paragraph("Generado: " + date, fHighText));
            paragraph.setSpacingAfter(30);

            document.add(paragraph);
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }
    }

    public void addImage(Drawable drawable){
        try {
            paragraph = new Paragraph();
            Drawable d = drawable;
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapData = stream.toByteArray();

            Image image = null;
            image = Image.getInstance(bitmapData);
            image.scaleAbsolute(500f, 650f);
            document.add(image);
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }
    }

    private void addChild(Paragraph chiParagraph){
        chiParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(chiParagraph);
    }

    public void addParagms(String text){
        try {
            paragraph = new Paragraph(text, fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }
    }

    public void createTable(String[] header, ArrayList<String[]>clients){
        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;
            while (indexC < header.length) {
                pdfPCell = new PdfPCell(new Phrase(header[indexC++], fSubtitle));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GREEN);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexRow = 0; indexRow < clients.size(); indexRow++) {
                String[] row = clients.get(indexRow);
                for (int indexCol = 0; indexCol < header.length; indexCol++) {
                    pdfPCell = new PdfPCell(new Phrase(row[indexCol]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPTable.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable);
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        }catch (Exception e){
            Log.d(TAG, e.toString());
        }
    }

    public void viewPdf(){
        Intent intent = new Intent(context, ViewPdf.class);
        intent.putExtra("path", pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
