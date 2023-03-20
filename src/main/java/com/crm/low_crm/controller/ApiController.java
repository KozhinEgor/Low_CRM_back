package com.crm.low_crm.controller;
import com.crm.low_crm.model.dto.HistoryFilter;
import com.crm.low_crm.model.enity.HistoryCall;
import com.crm.low_crm.model.enumerate.Period;
import com.crm.low_crm.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ApiController {


    @Autowired
    private HistoryService serviceH;

    @GetMapping("/addToday")
    private List<HistoryCall> addToday(){ return serviceH.addHistory(Period.today);}

    @GetMapping("/getAll")
    private List<HistoryCall> getAllHistory(){
        return serviceH.allHistory();
    }

    @PostMapping("/addPeriod")
    private List<HistoryCall> addPeriod(@RequestBody String period){
        log.info(period);
        return serviceH.addHistory(Period.decode(period));
    }

    @PostMapping("/findByDate")
    private List<HistoryCall> findByDate(@RequestBody HistoryFilter filter){
        return serviceH.findbyDate(filter);
    }

    @PostMapping("/addByFilter")
    private String addStartAndEnd(@RequestBody HistoryFilter historyFilter){
        serviceH.addHistory(historyFilter);
        return "ok";
    }
    @GetMapping("/test")
    private String test(){
        return "ок";
    }


//    @GetMapping("/AllInFile")
//    @ResponseBody
//    ResponseEntity<Resource> downloadFile() throws IOException {
//        List<HistoryCall> calls = serviceH.allHistory();
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        CreationHelper createHelper = workbook.getCreationHelper();
//        XSSFSheet sheet = workbook.createSheet("Станица");
//        XSSFColor colorborder = new XSSFColor(new java.awt.Color(0, 90, 170));
//
//        XSSFCellStyle hlinkstyle = workbook.createCellStyle();
//        XSSFFont hlinkfont = workbook.createFont();
//        hlinkfont.setUnderline(XSSFFont.U_SINGLE);
//        hlinkfont.setColor(new XSSFColor(new java.awt.Color(30, 144, 255)));
//        hlinkstyle.setFont(hlinkfont);
//        hlinkstyle.setWrapText(true);
//        hlinkstyle.setBorderTop(BorderStyle.THIN);
//        hlinkstyle.setBorderColor(XSSFCellBorder.BorderSide.TOP, colorborder);
//        hlinkstyle.setBorderRight(BorderStyle.THIN);
//        hlinkstyle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, colorborder);
//        hlinkstyle.setBorderBottom(BorderStyle.THIN);
//        hlinkstyle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, colorborder);
//        hlinkstyle.setBorderLeft(BorderStyle.THIN);
//        hlinkstyle.setBorderColor(XSSFCellBorder.BorderSide.LEFT, colorborder);
//
//        XSSFCellStyle body = workbook.createCellStyle();
//        body.setBorderTop(BorderStyle.THIN);
//        body.setBorderColor(XSSFCellBorder.BorderSide.TOP, colorborder);
//        body.setBorderRight(BorderStyle.THIN);
//        body.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, colorborder);
//        body.setBorderBottom(BorderStyle.THIN);
//        body.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, colorborder);
//        body.setBorderLeft(BorderStyle.THIN);
//        body.setBorderColor(XSSFCellBorder.BorderSide.LEFT, colorborder);
//        body.setWrapText(true);
//
//        XSSFCellStyle header = workbook.createCellStyle();
//        header.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 102, 204)));
//        header.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        XSSFFont headerFont = workbook.createFont();
//        headerFont.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
//        header.setFont(headerFont);
//
//
//
//        XSSFCellStyle cellStyle = workbook.createCellStyle();
//        XSSFDataFormat dateFormat = (XSSFDataFormat) workbook.createDataFormat();
//        cellStyle.setDataFormat(dateFormat.getFormat("dd.MM.yyyy HH:mm:ss"));
////       cellStyle.setDataFormat(
////               createHelper.createDataFormat().getFormat("dd.MM.yyyy HH:mm:ss"));
//        // cellStyle.setWrapText(true);
//        cellStyle.setBorderTop(BorderStyle.THIN);
//        cellStyle.setBorderColor(XSSFCellBorder.BorderSide.TOP, colorborder);
//        cellStyle.setBorderRight(BorderStyle.THIN);
//        cellStyle.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, colorborder);
//        cellStyle.setBorderBottom(BorderStyle.THIN);
//        cellStyle.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, colorborder);
//        cellStyle.setBorderLeft(BorderStyle.THIN);
//        cellStyle.setBorderColor(XSSFCellBorder.BorderSide.LEFT, colorborder);
//        int numberRow = 0;
//        XSSFRow row = sheet.createRow(numberRow);
//        sheet.setColumnWidth(0, 39 * 256);
//        sheet.setColumnWidth(1, 14 * 256);
//        sheet.setColumnWidth(2, 14 * 256);
//        sheet.setColumnWidth(3, 50 * 256);
//        sheet.setColumnWidth(4, 39 * 256);
//        sheet.setColumnWidth(5, 13 * 256);
//        sheet.setColumnWidth(6, 14 * 256);
//        sheet.setColumnWidth(7, 17 * 256);
//        sheet.setColumnWidth(8, 5 * 256);
//        sheet.setColumnWidth(9, 5 * 256);
//        sheet.setColumnWidth(10, 20 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
//        sheet.setColumnWidth(12, 12 * 256);
//        sheet.setColumnWidth(13, 12 * 256);
//        sheet.setColumnWidth(14, 12 * 256);
//        sheet.setColumnWidth(15, 56 * 256);
//        sheet.setColumnWidth(16, 39 * 256);
//        sheet.setColumnWidth(17, 20 * 256);
//        row.createCell(0).setCellValue("UID");
//        row.getCell(0).setCellStyle(header);
//        row.createCell(1).setCellValue("Тип звонка");
//        row.getCell(1).setCellStyle(header);
//        row.createCell(2).setCellValue("Клиент");
//        row.getCell(2).setCellStyle(header);
//        row.createCell(3).setCellValue("Логин сотрудника");
//        row.getCell(3).setCellStyle(header);
//        row.createCell(4).setCellValue("Номер телефона для исходящего звонка");
//        row.getCell(4).setCellStyle(header);
//        row.createCell(5).setCellValue("Начало");
//        row.getCell(5).setCellStyle(header);
//        row.createCell(6).setCellValue("Ожидание на линии");
//        row.getCell(6).setCellStyle(header);
//        row.createCell(7).setCellValue("Длительность разговора");
//        row.getCell(7).setCellStyle(header);
//        row.createCell(8).setCellValue("Запись");
//        row.getCell(8).setCellStyle(header);
//        row.createCell(9).setCellValue("Оценка качества");
//        row.getCell(9).setCellStyle(header);
//        for (HistoryCall call : calls) {
//            numberRow += 1;
//            row = sheet.createRow(numberRow);
//            row.setHeight((short) -1);
//            row.createCell(0).setCellValue(call.getUid());
//            row.getCell(0).setCellStyle(body);
//            row.createCell(1).setCellValue(call.getType().name());
//            row.getCell(1).setCellStyle(body);
//            row.createCell(2).setCellValue(call.getClient());
//            row.getCell(2).setCellStyle(body);
//            row.createCell(3).setCellValue(call.getAccount());
//            row.getCell(3).setCellStyle(body);
//            row.createCell(4).setCellValue(call.getVia());
//            row.getCell(4).setCellStyle(body);
//            row.createCell(5).setCellValue(call.getStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
//            row.getCell(5).setCellStyle(body);
//            row.createCell(6).setCellValue(call.getWait());
//            row.getCell(6).setCellStyle(body);
//            row.createCell(7).setCellValue(call.getDuration());
//            row.getCell(7).setCellStyle(body);
//            XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
//            link.setAddress(call.getRecord());
//            row.createCell(8).setCellValue("record");
//            row.getCell(8).setHyperlink((XSSFHyperlink) link);
//            row.getCell(8).setCellStyle(hlinkstyle);
//            row.createCell(9).setCellValue(call.getQualityControl());
//            row.getCell(9).setCellStyle(body);
//
//        }
//        File file = new File(pathname);
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        workbook.write(fileOutputStream);
//        fileOutputStream.close();
//        Resource file1 = fileService.download("temp.xlsx");
//        Path path = file1.getFile()
//                .toPath();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file1.getFilename() + "\"")
//                .body(file1);
//    }
}
