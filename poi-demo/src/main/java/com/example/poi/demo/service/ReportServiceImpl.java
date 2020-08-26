package com.example.poi.demo.service;

import com.example.poi.demo.model.ColumnInformation;
import com.example.poi.demo.model.Field;
import com.example.poi.demo.transversal.Constant;
import com.example.poi.demo.util.DataUtil;
import com.example.poi.demo.util.DateUtil;
import com.example.poi.demo.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    @SneakyThrows
    public Resource getReport() {
        List<Map<String,Object>> data = new JsonUtil().parseFromJSONFile(new TypeReference<List<Map<String,Object>>>(){}, "data.json");
        List<ColumnInformation> columns = new JsonUtil().parseFromJSONFile(new TypeReference<List<ColumnInformation>>(){}, "fieldInformation.json");
        List<Field> customFields = generateFieldsReport(columns);
        List<String> cellTypes = buildCellTypes(columns);
        List<String[]> dataLines = buildDataLines(customFields, data);

        return new ByteArrayResource(createXlsxFileAsByteArray(cellTypes, dataLines, "reporte_pruebita"));
    }

    private byte[] createXlsxFileAsByteArray(List<String> cellTypes, List<String[]> dataLines, String sheetName) throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();

        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        Sheet sheet = workbook.createSheet(sheetName);
        int rowNum = -1;

        CreationHelper helper = workbook.getCreationHelper();
        CellStyle cellStyleDateTime = workbook.createCellStyle();
        cellStyleDateTime.setDataFormat(helper.createDataFormat().getFormat("dd/MM/yyyy"));

        for (String[] dataLine : dataLines) {
            ++rowNum;
            Row currentRow = sheet.createRow(rowNum);

            for (int i = 0; i < dataLine.length; ++i) {
                if (rowNum > 0) {
                    switch (cellTypes.get(i)) {
                        case Constant.TYPE_DOUBLE:
                            if (dataLine[i] == null || dataLine[i].equals(""))
                                currentRow.createCell(i).setCellValue("");
                            else
                                currentRow.createCell(i).setCellValue(Double.parseDouble(dataLine[i]));
                            break;
                        case Constant.TYPE_INTEGER:
                            if (dataLine[i] == null || dataLine[i].equals(""))
                                currentRow.createCell(i).setCellValue("");
                            else
                                currentRow.createCell(i).setCellValue((double) Integer.parseInt(dataLine[i]));
                            break;
                        case Constant.TYPE_DATE:
                            if (dataLine[i] == null || dataLine[i].equals(""))
                                currentRow.createCell(i).setCellValue("");
                            else {
                                currentRow.createCell(i).setCellValue(DateUtil.toDate(dataLine[i], Constant.FORMAT_DATE_CELL));
                                currentRow.getCell(i).setCellStyle(cellStyleDateTime);
                            }
                            break;
                        default:
                            if (dataLine[i] == null || dataLine[i].equals(""))
                                currentRow.createCell(i).setCellValue("");
                            else
                                currentRow.createCell(i).setCellValue(dataLine[i]);
                            break;
                    }
                } else {
                    if (dataLine[i] == null || dataLine[i].equals(""))
                        currentRow.createCell(i).setCellValue("");
                    else
                        currentRow.createCell(i).setCellValue(dataLine[i]);

                    CellStyle cellStyle = workbook.createCellStyle();
                    cellStyle.setFillBackgroundColor(IndexedColors.VIOLET.getIndex());
                    cellStyle.setFillPattern(FillPatternType.BIG_SPOTS);
                    Font font = workbook.createFont();
                    font.setColor(IndexedColors.WHITE.getIndex());
                    cellStyle.setFont(font);
                    currentRow.getCell(i).setCellStyle(cellStyle);
                }
            }
        }

        workbook.write(byteArray);

        return byteArray.toByteArray();
    }

    private List<Field> generateFieldsReport(List<ColumnInformation> columnInformation) {
        return columnInformation.stream().map(r -> {
            Field field = new Field();
            field.setName(r.getDescriptionSql());
            field.setDescription(r.getDescriptionField());

            switch (r.getFieldType()) {
                case Constant.TYPE_STRING:
                    field.setType(Field.Type.STRING);
                    break;
                case Constant.TYPE_DATE:
                    field.setType(Field.Type.DATE);
                    break;
                case Constant.TYPE_DOUBLE:
                    field.setType(Field.Type.DOUBLE);
                    break;
                case Constant.TYPE_INTEGER:
                    field.setType(Field.Type.INTEGER);
                    break;
                default:
                    break;
            }

            return field;
        }).collect(Collectors.toList());
    }

    private List<String> buildCellTypes(List<ColumnInformation> customFields) {
        return customFields.stream().map(ColumnInformation::getFieldType).collect(Collectors.toList());
    }

    private List<String[]> buildDataLines(List<Field> fields, List<Map<String, Object>> mapList) {
        List<String[]> dataLines = new ArrayList<>();
        String[] headLine = fields.stream().map(Field::getDescription).toArray(String[]::new);
        dataLines.add(headLine);

        List<String[]> bodyLines = mapList.stream().map(m -> fields.stream().map(f -> {
            String value = "";

            if (!Objects.nonNull(m.get(f.getName()))) {
                return value;
            }

            switch (f.getType()) {
                case STRING:
                case INTEGER:
                    value = m.get(f.getName()).toString();
                    break;
                case DATE:
                    value = DateUtil.formatDate(new Date((Long)m.get(f.getName()) * 1000), Constant.XLSX_DATE_PATTERN);
                    break;
                case DOUBLE:
                    value = DataUtil.formatDecimal((Double) m.get(f.getName()), Constant.DECIMAL_PATTERN);
                    break;
                default:
                    break;
            }

            return value;
        }).toArray(String[]::new)).collect(Collectors.toList());
        dataLines.addAll(bodyLines);

        return dataLines;
    }
}
